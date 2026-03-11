package com.apelisser.algashop.product.catalog.application.product.management;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CategoryInput {

    @NotBlank
    private String name;

    @NotNull
    private Boolean enabled;

    public CategoryInput() {
    }

    public CategoryInput(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

}
