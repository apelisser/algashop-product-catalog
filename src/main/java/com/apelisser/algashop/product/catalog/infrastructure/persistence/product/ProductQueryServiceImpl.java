package com.apelisser.algashop.product.catalog.infrastructure.persistence.product;

import com.apelisser.algashop.product.catalog.application.PageModel;
import com.apelisser.algashop.product.catalog.application.product.query.ProductDetailOutput;
import com.apelisser.algashop.product.catalog.application.product.query.ProductQueryService;
import com.apelisser.algashop.product.catalog.application.product.query.ProductSumaryOutput;
import com.apelisser.algashop.product.catalog.application.utility.Mapper;
import com.apelisser.algashop.product.catalog.domain.model.product.Product;
import com.apelisser.algashop.product.catalog.domain.model.product.ProductNotFoundException;
import com.apelisser.algashop.product.catalog.domain.model.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final Mapper mapper;

    public ProductQueryServiceImpl(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDetailOutput findById(UUID productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(productId));
        return mapper.convert(product, ProductDetailOutput.class);
    }

    @Override
    public PageModel<ProductSumaryOutput> filter(Integer size, Integer number) {
        return null;
    }

}
