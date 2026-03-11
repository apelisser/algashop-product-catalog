package com.apelisser.algashop.product.catalog.application.product.query;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CategoryDetailOutput {

    private UUID id;
    private String name;
    private Boolean enabled;

    public CategoryDetailOutput() {
    }

    public CategoryDetailOutput(UUID id, String name, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
    }

}
