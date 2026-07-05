package com.apelisser.algashop.product.catalog.domain.model.product;

import com.apelisser.algashop.product.catalog.domain.model.DomainException;
import com.apelisser.algashop.product.catalog.domain.model.IdGenerator;
import com.apelisser.algashop.product.catalog.domain.model.category.Category;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@Document(collection = "products")
public class Product {

    @Id
    private UUID id;

    private String name;

    private String brand;

    private String description;

    private Integer quantityInStock;

    private Boolean enabled;

    private BigDecimal regularPrice;

    private BigDecimal salePrice;

    private Integer discountPercentageRounded;

    @DocumentReference
    @Field(name = "categoryId")
    private Category category;

    @Version
    private Long version;

    @CreatedDate
    private OffsetDateTime addedAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;

    @CreatedBy
    private UUID createdByUserId;

    @LastModifiedBy
    private UUID lastModifiedByUserId;

    protected Product() {
    }

    @Builder
    public Product(String name, String brand, String description, Boolean enabled, BigDecimal regularPrice,
            BigDecimal salePrice, Category category) {
        this.setId(IdGenerator.generateTimeBasedUUID());
        this.setName(name);
        this.setBrand(brand);
        this.setDescription(description);
        this.setEnabled(enabled);
        this.setRegularPrice(regularPrice);
        this.setSalePrice(salePrice);
        this.setCategory(category);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be null or blank");
        }
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        if (regularPrice == null) {
            throw new IllegalArgumentException("Regular price cannot be null");
        }

        if (regularPrice.signum() == -1) {
            throw new IllegalArgumentException("Regular price cannot be negative");
        }

        if (this.salePrice == null) {
            this.salePrice = regularPrice;
        } else if (regularPrice.compareTo(this.salePrice) < 0) {
            throw new DomainException("Sale price cannot be greater than regular price");
        }

        this.regularPrice = regularPrice;
        this.calculateDiscountPercentage();
    }

    public void setSalePrice(BigDecimal salePrice) {
        if (salePrice == null) {
            throw new IllegalArgumentException("Regular price cannot be null");
        }

        if (salePrice.signum() == -1) {
            throw new IllegalArgumentException("Regular price cannot be negative");
        }

        if (this.regularPrice == null) {
            this.regularPrice = salePrice;
        } else if (this.regularPrice.compareTo(salePrice) < 0) {
            throw new DomainException("Sale price cannot be greater than regular price");
        }

        this.salePrice = salePrice;
        this.calculateDiscountPercentage();
    }

    public void setCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        this.category = category;
    }

    public void setEnabled(Boolean enabled) {
        if (enabled == null) {
            throw new IllegalArgumentException("Enabled cannot be null");
        }

        this.enabled = enabled;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public boolean isInStock() {
        return this.getQuantityInStock() != null && this.getQuantityInStock() > 0;
    }

    public boolean getHasDiscount() {
        return getDiscountPercentageRounded() != null && getDiscountPercentageRounded() > 0;
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.id = id;
    }

    private void setQuantityInStock(Integer quantityInStock) {
        if (quantityInStock == null) {
            throw new IllegalArgumentException("Quantity in stock cannot be null");
        }
        if (quantityInStock < 0) {
            throw new IllegalArgumentException("Quantity in stock cannot be negative");
        }
        this.quantityInStock = quantityInStock;
    }

    private void calculateDiscountPercentage() {
        if (regularPrice == null || salePrice == null || regularPrice.signum() == 0) {
            this.discountPercentageRounded = 0;
            return;
        }

        discountPercentageRounded = BigDecimal.ONE
            .subtract(salePrice.divide(regularPrice, 4, RoundingMode.HALF_UP))
            .multiply(BigDecimal.valueOf(100))
            .setScale(0, RoundingMode.HALF_UP)
            .intValue();

    }

}
