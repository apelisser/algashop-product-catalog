package com.apelisser.algashop.product.catalog.application.category.management;

import com.apelisser.algashop.product.catalog.application.ResourceNotFoundException;
import com.apelisser.algashop.product.catalog.domain.model.category.Category;
import com.apelisser.algashop.product.catalog.domain.model.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryManagementApplicationService {

    private final CategoryRepository categoryRepository;

    public CategoryManagementApplicationService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public UUID create(CategoryInput input) {
        Category category = new Category(input.getName(), input.getEnabled());
        categoryRepository.save(category);
        return category.getId();
    }

    public void update(UUID categoryId, CategoryInput input) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(ResourceNotFoundException::new);
        category.setName(input.getName());
        category.setEnabled(input.getEnabled());
        categoryRepository.save(category);
    }

    public void disable(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(ResourceNotFoundException::new);
        category.setEnabled(false);
        categoryRepository.save(category);
    }

}
