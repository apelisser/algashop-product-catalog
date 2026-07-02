package com.apelisser.algashop.product.catalog.application.product.management;

import com.apelisser.algashop.product.catalog.application.ResourceNotFoundException;
import com.apelisser.algashop.product.catalog.domain.model.category.Category;
import com.apelisser.algashop.product.catalog.domain.model.category.CategoryRepository;
import com.apelisser.algashop.product.catalog.domain.model.product.Product;
import com.apelisser.algashop.product.catalog.domain.model.product.ProductRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductManagementApplicationService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductManagementApplicationService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public UUID create(ProductInput input) {
        Product product = mapToProduct(input);
        productRepository.save(product);
        return product.getId();
    }

    public void update(UUID productId, ProductInput input) {

    }

    public void disable(UUID productId) {

    }

    private Product mapToProduct(ProductInput input) {
        Category category = findCategory(input.getCategoryId());

        return Product.builder()
            .name(input.getName())
            .brand(input.getBrand())
            .description(input.getDescription())
            .regularPrice(input.getRegularPrice())
            .salePrice(input.getSalePrice())
            .enabled(input.getEnabled())
            .build();
    }

    private Category findCategory(@NotNull UUID categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(ResourceNotFoundException::new);
    }

}
