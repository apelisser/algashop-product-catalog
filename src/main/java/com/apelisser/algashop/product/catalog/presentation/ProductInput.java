package com.apelisser.algashop.product.catalog.presentation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ProductInput {

    private String name;
    private String brand;
    private BigDecimal regularPrice;
    private BigDecimal salePrice;
    private Boolean enabled;
    private UUID categoryId;
    private String description;

    public ProductInput() {
    }

    public ProductInput(String name, String brand, BigDecimal regularPrice, BigDecimal salePrice, Boolean enabled,
            UUID categoryId, String description) {
        this.name = name;
        this.brand = brand;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.enabled = enabled;
        this.categoryId = categoryId;
        this.description = description;
    }

}
