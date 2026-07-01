package com.apelisser.algashop.product.catalog.domain.model.category;

import com.apelisser.algashop.product.catalog.domain.model.IdGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@Document(collection = "categories")
public class Category {

    @Id
    private UUID id;

    private String name;

    private Boolean enabled;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    protected Category() {
    }

    public Category(String name, Boolean enabled) {
        this.id = IdGenerator.generateTimeBasedUUID();
        this.setName(name);
        this.setEnabled(enabled);
        this.createdAt = OffsetDateTime.now();
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = Objects.requireNonNull(enabled, "Enabled cannot be null");
    }

}
