package com.apelisser.algashop.product.catalog.contract.base;

import com.apelisser.algashop.product.catalog.application.product.management.ProductManagementApplicationService;
import com.apelisser.algashop.product.catalog.application.product.query.PageModel;
import com.apelisser.algashop.product.catalog.application.product.query.ProductDetailOutput;
import com.apelisser.algashop.product.catalog.application.product.query.ProductDetailOutputTestDataBuilder;
import com.apelisser.algashop.product.catalog.application.product.query.ProductQueryService;
import com.apelisser.algashop.product.catalog.presentation.ProductController;
import com.apelisser.algashop.product.catalog.presentation.ProductInput;
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

    @BeforeEach
    void setUp() {
        System.out.println(UUID.randomUUID().toString());
        RestAssuredMockMvc.mockMvc(
            MockMvcBuilders.webAppContextSetup(context)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build());

        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();

        mockValidOrderFindById();
        mockFilterProducts();
        mockCreateProduct();
    }

    private void mockCreateProduct() {
        Mockito.when(productManagementApplicationService.create(Mockito.any(ProductInput.class)))
            .thenReturn(validProductId);
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

    private void mockValidOrderFindById() {
        Mockito.when(productQueryService.findById(Mockito.any(UUID.class)))
            .thenReturn(ProductDetailOutputTestDataBuilder.aProduct().id(validProductId).build());
    }

}
