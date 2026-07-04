package com.apelisser.algashop.product.catalog.infrastructure.persistence.category;

import com.apelisser.algashop.product.catalog.application.PageModel;
import com.apelisser.algashop.product.catalog.application.category.query.CategoryDetailOutput;
import com.apelisser.algashop.product.catalog.application.category.query.CategoryQueryService;
import com.apelisser.algashop.product.catalog.application.utility.Mapper;
import com.apelisser.algashop.product.catalog.domain.model.category.Category;
import com.apelisser.algashop.product.catalog.domain.model.category.CategoryNotFoundException;
import com.apelisser.algashop.product.catalog.domain.model.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    public CategoryQueryServiceImpl(CategoryRepository categoryRepository, Mapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDetailOutput findById(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return mapper.convert(category, CategoryDetailOutput.class);
    }

    @Override
    public PageModel<CategoryDetailOutput> filter(Integer size, Integer number) {
        return null;
    }

}
