package com.apelisser.algashop.product.catalog.domain.model;

import java.io.Serial;

public class DomainEntityNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DomainEntityNotFoundException() {
    }

    public DomainEntityNotFoundException(String message) {
        super(message);
    }

    public DomainEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainEntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public DomainEntityNotFoundException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
