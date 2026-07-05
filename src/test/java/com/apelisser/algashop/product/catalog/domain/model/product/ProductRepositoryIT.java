package com.apelisser.algashop.product.catalog.domain.model.product;

import com.apelisser.algashop.product.catalog.infrastructure.persistence.MongoConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Import(MongoConfig.class)
class ProductRepositoryIT {

    static final Logger log = LoggerFactory.getLogger(ProductRepositoryIT.class);

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldFilter() {
        Page<ProductNameProjection> products = productRepository.findAllByEnabled(true, PageRequest.of(0, 3));
        products.forEach(product -> log.info("Product - Id: {}, Name: {}", product.id(), product.name()));
    }

}