package com.apelisser.algashop.product.catalog.presentation;

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
public class CategoryMinimalOutput {

    private UUID id;
    private String name;

    public CategoryMinimalOutput() {
    }

    public CategoryMinimalOutput(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}
