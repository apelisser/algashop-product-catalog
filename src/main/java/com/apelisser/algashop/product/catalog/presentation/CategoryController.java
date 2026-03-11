package com.apelisser.algashop.product.catalog.presentation;

import com.apelisser.algashop.product.catalog.application.product.management.CategoryInput;
import com.apelisser.algashop.product.catalog.application.product.management.CategoryManagementApplicationService;
import com.apelisser.algashop.product.catalog.application.product.query.CategoryDetailOutput;
import com.apelisser.algashop.product.catalog.application.product.query.CategoryQueryService;
import com.apelisser.algashop.product.catalog.application.product.query.PageModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryQueryService categoryQueryService;
    private final CategoryManagementApplicationService categoryManagementApplicationService;

    public CategoryController(CategoryQueryService categoryQueryService,
            CategoryManagementApplicationService categoryManagementApplicationService) {
        this.categoryQueryService = categoryQueryService;
        this.categoryManagementApplicationService = categoryManagementApplicationService;
    }

    @GetMapping
    public PageModel<CategoryDetailOutput> filter(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "number", required = false) Integer number) {
        return categoryQueryService.filter(size, number);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDetailOutput create(@RequestBody @Valid CategoryInput input) {
        UUID categoryId = categoryManagementApplicationService.create(input);
        return categoryQueryService.findById(categoryId);
    }

    @GetMapping("/{categoryId}")
    public CategoryDetailOutput findById(@PathVariable UUID categoryId) {
        return categoryQueryService.findById(categoryId);
    }

    @PutMapping("/{categoryId}")
    public CategoryDetailOutput update(@PathVariable UUID categoryId, @RequestBody @Valid CategoryInput input) {
        categoryManagementApplicationService.update(categoryId, input);
        return categoryQueryService.findById(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable UUID categoryId) {
        categoryManagementApplicationService.disable(categoryId);
    }

}
