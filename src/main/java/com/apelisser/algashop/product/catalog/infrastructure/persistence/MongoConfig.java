package com.apelisser.algashop.product.catalog.infrastructure.persistence;

import org.bson.UuidRepresentation;
import org.springframework.boot.mongodb.autoconfigure.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Comentando em favor da configuração do application.yaml
spring:
  mongodb:
    representation.uuid: standard
*/

//@Configuration
public class MongoConfig {

    //@Bean
    public MongoClientSettingsBuilderCustomizer uuidCustomizer() {
        return builder -> builder.uuidRepresentation(UuidRepresentation.STANDARD);
    }

}
