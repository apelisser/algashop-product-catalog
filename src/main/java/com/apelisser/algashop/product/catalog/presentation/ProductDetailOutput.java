package com.apelisser.algashop.product.catalog.presentation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ProductDetailOutput {

    private UUID id;
    private OffsetDateTime addedAt;
    private String name;
    private String brand;
    private BigDecimal regularPrice;
    private BigDecimal salePrice;
    private Boolean inStock;
    private Boolean enabled;
    private UUID categoryId;
    private String description;

    public ProductDetailOutput() {
    }

    public ProductDetailOutput(UUID id, OffsetDateTime addedAt, String name, String brand, BigDecimal regularPrice,
            BigDecimal salePrice, Boolean inStock, Boolean enabled, UUID categoryId, String description) {
        this.id = id;
        this.addedAt = addedAt;
        this.name = name;
        this.brand = brand;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.inStock = inStock;
        this.enabled = enabled;
        this.categoryId = categoryId;
        this.description = description;
    }

}
