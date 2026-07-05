package com.apelisser.algashop.product.catalog.application.product.query;

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
public class ProductSumaryOutput {

    private UUID id;
    private OffsetDateTime addedAt;
    private String name;
    private String brand;
    private BigDecimal regularPrice;
    private BigDecimal salePrice;
    private Boolean inStock;
    private Boolean enabled;
    private UUID categoryId;
    private CategoryMinimalOutput category;
    private String shortDescription;
    private String slug;
    private Boolean hasDiscount;
    private Integer quantityInStock;
    private Integer discountPercentageRounded;

    public ProductSumaryOutput() {
    }

    public ProductSumaryOutput(UUID id, OffsetDateTime addedAt, String name, String brand, BigDecimal regularPrice,
            BigDecimal salePrice, Boolean inStock, Boolean enabled, UUID categoryId, CategoryMinimalOutput category,
            String shortDescription, String slug, Boolean hasDiscount, Integer quantityInStock,
            Integer discountPercentageRounded) {
        this.id = id;
        this.addedAt = addedAt;
        this.name = name;
        this.brand = brand;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.inStock = inStock;
        this.enabled = enabled;
        this.categoryId = categoryId;
        this.category = category;
        this.shortDescription = shortDescription;
        this.slug = slug;
        this.hasDiscount = hasDiscount;
        this.quantityInStock = quantityInStock;
        this.discountPercentageRounded = discountPercentageRounded;
    }

}
