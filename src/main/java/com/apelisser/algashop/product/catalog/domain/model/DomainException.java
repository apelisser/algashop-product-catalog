package com.apelisser.algashop.product.catalog.domain.model;

import java.io.Serial;

public class DomainException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DomainException() {
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }

    public DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
