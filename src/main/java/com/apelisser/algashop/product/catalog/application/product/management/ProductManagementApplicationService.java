package com.apelisser.algashop.product.catalog.application.product.management;

import com.apelisser.algashop.product.catalog.domain.model.category.Category;
import com.apelisser.algashop.product.catalog.domain.model.category.CategoryNotFoundException;
import com.apelisser.algashop.product.catalog.domain.model.category.CategoryRepository;
import com.apelisser.algashop.product.catalog.domain.model.product.Product;
import com.apelisser.algashop.product.catalog.domain.model.product.ProductNotFoundException;
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
        Product product = findProduct(productId);
        Category category = findCategory(input.getCategoryId());

        updateProduct(product, input);
        product.setCategory(category);
        productRepository.save(product);
    }

    public void disable(UUID productId) {
        Product product = findProduct(productId);
        product.disable();
        productRepository.save(product);
    }

    public void enable(UUID productId) {
        Product product = findProduct(productId);
        product.enable();
        productRepository.save(product);
    }

    private void updateProduct(Product product, ProductInput input) {
        product.setName(input.getName());
        product.setBrand(input.getBrand());
        product.setDescription(input.getDescription());
        product.setRegularPrice(input.getRegularPrice());
        product.setSalePrice(input.getSalePrice());
        product.setEnabled(input.getEnabled());
    }

    private Product findProduct(UUID productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(productId));
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
            .category(category)
            .build();
    }

    private Category findCategory(@NotNull UUID categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

}
