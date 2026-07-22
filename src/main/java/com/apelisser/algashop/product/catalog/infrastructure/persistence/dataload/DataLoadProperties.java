package com.apelisser.algashop.product.catalog.infrastructure.persistence.dataload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component
@ConfigurationProperties("algashop.data-load")
@Validated
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DataLoadProperties {

    @NotNull
    private Boolean enabled;

    private Boolean autoDrop;

    @Valid
    private List<DataLoadSource> sources;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class DataLoadSource {

        @NotBlank
        private String location;

        @NotBlank
        private String collection;

    }

}
