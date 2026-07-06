package com.apelisser.algashop.product.catalog.application.product.query;

import com.apelisser.algashop.product.catalog.application.utility.SortablePageFilter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductFilter extends SortablePageFilter<ProductFilter.SortType> {

    private String term;
    private Boolean hasDiscount;
    private Boolean enabled;
    private Boolean inStock;

    private BigDecimal priceFrom;
    private BigDecimal priceTo;

    private UUID[] categoriesId;

    private OffsetDateTime addedAtFrom;
    private OffsetDateTime addedAtTo;

    public ProductFilter() {
    }

    public ProductFilter(int page, int size) {
        super(page, size);
    }

    public ProductFilter(String term, Boolean hasDiscount, Boolean enabled, Boolean inStock, BigDecimal priceFrom,
            BigDecimal priceTo, UUID[] categoriesId, OffsetDateTime addedAtFrom, OffsetDateTime addedAtTo) {
        this.term = term;
        this.hasDiscount = hasDiscount;
        this.enabled = enabled;
        this.inStock = inStock;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.categoriesId = categoriesId;
        this.addedAtFrom = addedAtFrom;
        this.addedAtTo = addedAtTo;
    }

    @Override
    public SortType getSortByPropertyOrDefault() {
        SortType sortByProperty = getSortByProperty();
        return sortByProperty != null
            ? sortByProperty
            : SortType.ADDED_AT;
    }

    @Override
    public Sort.Direction getSortDirectionOrDefault() {
        Sort.Direction sortDirection = getSortDirection();
        return sortDirection != null
            ? sortDirection
            : Sort.Direction.ASC;
    }

    @Getter
    public enum SortType {
        ADDED_AT("addedAt"),
        SALE_PRICE("salePrice");

        private final String property;

        SortType(String property) {
            this.property = property;
        }
    }

}
