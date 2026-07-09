package com.apelisser.algashop.product.catalog.infrastructure.persistence.product;

import com.apelisser.algashop.product.catalog.application.PageModel;
import com.apelisser.algashop.product.catalog.application.product.query.ProductDetailOutput;
import com.apelisser.algashop.product.catalog.application.product.query.ProductFilter;
import com.apelisser.algashop.product.catalog.application.product.query.ProductQueryService;
import com.apelisser.algashop.product.catalog.application.product.query.ProductSumaryOutput;
import com.apelisser.algashop.product.catalog.application.utility.Mapper;
import com.apelisser.algashop.product.catalog.domain.model.product.Product;
import com.apelisser.algashop.product.catalog.domain.model.product.ProductNotFoundException;
import com.apelisser.algashop.product.catalog.domain.model.product.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final Mapper mapper;

    private final MongoOperations mongoOperations;

    public ProductQueryServiceImpl(ProductRepository productRepository, Mapper mapper, MongoOperations mongoOperations) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public ProductDetailOutput findById(UUID productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(productId));
        return mapper.convert(product, ProductDetailOutput.class);
    }

    @Override
    public PageModel<ProductSumaryOutput> filter(ProductFilter filter) {
        Query query = queryWith(filter);

        long totalItems = mongoOperations.count(query, Product.class);
        Sort sort = sortWith(filter);

        PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getSize(), sort);

        Query pagedQuery = query.with(pageRequest);

        List<Product> products;
        int totalPages = 0;
        if (totalItems > 0) {
            products = mongoOperations.find(pagedQuery, Product.class);
            totalPages = (int) Math.ceil((double) totalItems / pageRequest.getPageSize());
        } else {
            products = new ArrayList<>();
        }

        List<ProductSumaryOutput> productOutputs = products.stream()
            .map(product -> mapper.convert(product, ProductSumaryOutput.class))
            .collect(Collectors.toList());

        return PageModel.<ProductSumaryOutput>builder()
            .content(productOutputs)
            .number(pageRequest.getPageSize())
            .size(pageRequest.getPageSize())
            .totalElements(totalItems)
            .totalPages(totalPages)
            .build();
    }

    private Sort sortWith(ProductFilter filter) {
        return Sort.by(
            filter.getSortDirectionOrDefault(),
            filter.getSortByPropertyOrDefault().getPropertyName()
        );
    }

    private Query queryWith(ProductFilter filter) {
        Query query = new Query();

        if (filter.getEnabled() != null) {
            query.addCriteria(Criteria.where("enabled").is(filter.getEnabled()));
        }

        if (filter.getAddedAtFrom() != null && filter.getAddedAtTo() != null) {
            query.addCriteria(Criteria.where("addedAt")
                .gte(filter.getAddedAtFrom())
                .lte(filter.getAddedAtTo())
            );
        } else if (filter.getAddedAtFrom() != null) {
            query.addCriteria(Criteria.where("addedAt").gte(filter.getAddedAtFrom()));
        } else if (filter.getAddedAtTo() != null) {
            query.addCriteria(Criteria.where("addedAt").lte(filter.getAddedAtTo()));
        }


        return query;
    }

}
