package com.apelisser.algashop.product.catalog.contract.base;

import com.apelisser.algashop.product.catalog.application.ResourceNotFoundException;
import com.apelisser.algashop.product.catalog.application.product.management.ProductInput;
import com.apelisser.algashop.product.catalog.application.product.management.ProductManagementApplicationService;
import com.apelisser.algashop.product.catalog.application.product.query.PageModel;
import com.apelisser.algashop.product.catalog.application.product.query.ProductDetailOutput;
import com.apelisser.algashop.product.catalog.application.product.query.ProductDetailOutputTestDataBuilder;
import com.apelisser.algashop.product.catalog.application.product.query.ProductQueryService;
import com.apelisser.algashop.product.catalog.presentation.ProductController;
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

@WebMvcTest(controllers = ProductController.class)
public class ProductBase {

    @Autowired
    WebApplicationContext context;
    private Object mockMvc;

    @MockitoBean
    ProductQueryService productQueryService;

    @MockitoBean
    ProductManagementApplicationService productManagementApplicationService;

    public static final UUID validProductId = UUID.fromString("fffe4676-367b-4015-941a-41c31c3b3d3e");
    public static final UUID invalidProductId = UUID.fromString("21651a12-b126-4213-ac21-19f66ff4642e");
    public static final UUID createdProductId = UUID.fromString("cc4cb634-c849-431e-ba35-8f12156b6920");
    public static final UUID updatedInvalidProductId = UUID.fromString("bdc585c9-b535-469e-be44-208cc918b638");

    @BeforeEach
    void setUp() {
        System.out.println(UUID.randomUUID().toString());
        RestAssuredMockMvc.mockMvc(
            MockMvcBuilders.webAppContextSetup(context)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build());

        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();

        mockValidProductFindById();
        mockInvalidProductFindById();
        mockFilterProducts();
        mockCreateProduct();
        mockInvalidProductUpdate();
    }

    private void mockInvalidProductUpdate() {
        Mockito.doThrow(ResourceNotFoundException.class)
            .when(productManagementApplicationService).update(Mockito.eq(updatedInvalidProductId), Mockito.any(ProductInput.class));
    }

    private void mockInvalidProductFindById() {
        Mockito.when(productQueryService.findById(invalidProductId))
            .thenThrow(new ResourceNotFoundException());
    }

    private void mockCreateProduct() {
        Mockito.when(productManagementApplicationService.create(Mockito.any(ProductInput.class)))
            .thenReturn(createdProductId);

        Mockito.when(productQueryService.findById(createdProductId))
            .thenReturn(ProductDetailOutputTestDataBuilder.aProduct().inStock(false).build());
    }

    private void mockFilterProducts() {
        Mockito.when(productQueryService.filter(Mockito.anyInt(), Mockito.anyInt()))
            .then(answer -> {
                Integer size = answer.getArgument(0);

                return PageModel.<ProductDetailOutput>builder()
                    .number(0)
                    .size(size)
                    .totalPages(1)
                    .totalElements(2)
                    .content(
                        List.of(
                            ProductDetailOutputTestDataBuilder.aProduct().build(),
                            ProductDetailOutputTestDataBuilder.aProductAlt1().build()
                        )
                    ).build();
            });
    }

    private void mockValidProductFindById() {
        Mockito.when(productQueryService.findById(validProductId))
            .thenReturn(ProductDetailOutputTestDataBuilder.aProduct().id(validProductId).build());
    }

}
