package com.apelisser.algashop.product.catalog.contract.base;

import com.apelisser.algashop.product.catalog.application.ResourceNotFoundException;
import com.apelisser.algashop.product.catalog.application.product.management.CategoryInput;
import com.apelisser.algashop.product.catalog.application.product.management.CategoryManagementApplicationService;
import com.apelisser.algashop.product.catalog.application.product.query.CategoryDetailOutput;
import com.apelisser.algashop.product.catalog.application.product.query.CategoryQueryService;
import com.apelisser.algashop.product.catalog.application.product.query.PageModel;
import com.apelisser.algashop.product.catalog.presentation.CategoryController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@WebMvcTest(controllers = CategoryController.class)
public class CategoryBase {

    @Autowired
    WebApplicationContext context;

    @MockitoBean
    CategoryQueryService categoryQueryService;

    @MockitoBean
    CategoryManagementApplicationService categoryManagementApplicationService;

    public static final UUID validCategoryId = UUID.fromString("4f47cd20-9fa7-4fdc-973c-97d9689ec668");
    public static final UUID invalidCategoryId = UUID.fromString("df01c615-d379-4085-905b-a98ef51e5fd9");

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(
            MockMvcBuilders.webAppContextSetup(context)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build());

        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();

        mockValidCategoryFindById();
        mockInvalidCategoryFindById();
        mockCreateCategory();
        mockFilterCategories();
        mockInvalidCategoryUpdate();
        mockInvalidProductDisable();
    }

    private void mockInvalidProductDisable() {
        Mockito.doThrow(ResourceNotFoundException.class)
            .when(categoryManagementApplicationService).disable(invalidCategoryId);
    }

    private void mockInvalidCategoryUpdate() {
        Mockito.doThrow(ResourceNotFoundException.class)
            .when(categoryManagementApplicationService).update(Mockito.eq(invalidCategoryId), Mockito.any(CategoryInput.class));
    }

    private void mockValidCategoryFindById() {
        Mockito.when(categoryQueryService.findById(validCategoryId))
            .thenReturn(CategoryDetailOutput.builder()
                .id(validCategoryId)
                .name("Notebook")
                .enabled(true)
                .build());
    }

    private void mockInvalidCategoryFindById() {
        Mockito.when(categoryQueryService.findById(invalidCategoryId))
            .thenThrow(new ResourceNotFoundException());
    }

    private void mockCreateCategory() {
        Mockito.when(categoryManagementApplicationService.create(Mockito.any(CategoryInput.class)))
            .thenReturn(validCategoryId);
    }

    private void mockFilterCategories() {
        Mockito.when(categoryQueryService.filter(Mockito.anyInt(), Mockito.anyInt()))
            .then(answer -> {
                Integer size = answer.getArgument(0);

                return PageModel.<CategoryDetailOutput>builder()
                    .number(0)
                    .size(size)
                    .totalPages(1)
                    .totalElements(3)
                    .content(
                        List.of(
                            CategoryDetailOutput.builder().id(UUID.randomUUID()).name("Notebook").enabled(true).build(),
                            CategoryDetailOutput.builder().id(UUID.randomUUID()).name("Desktop").enabled(true).build(),
                            CategoryDetailOutput.builder().id(UUID.randomUUID()).name("Disquete").enabled(false).build()
                        )
                    ).build();
            });
    }

}
