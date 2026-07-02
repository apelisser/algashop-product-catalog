package com.apelisser.algashop.product.catalog.domain.model.product;

import com.apelisser.algashop.product.catalog.domain.model.IdGenerator;
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

import java.math.BigDecimal;
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
    public Product(String name, String brand, String description, Boolean enabled, BigDecimal regularPrice, BigDecimal salePrice) {
        this.id = IdGenerator.generateTimeBasedUUID();
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.enabled = enabled;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
    }

}
