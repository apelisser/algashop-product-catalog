package com.apelisser.algashop.product.catalog.application.product.query;

import com.apelisser.algashop.product.catalog.application.PageModel;

import java.util.UUID;

public interface ProductQueryService {

    ProductDetailOutput findById(UUID productId);

    PageModel<ProductSumaryOutput> filter(Integer size, Integer number);

}
