package com.apelisser.algashop.product.catalog.presentation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    @NotNull
    private BigDecimal regularPrice;

    @NotNull
    private BigDecimal salePrice;

    @NotNull
    private Boolean enabled;

    @NotNull
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
