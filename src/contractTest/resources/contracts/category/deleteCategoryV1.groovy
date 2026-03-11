package contracts.category

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method DELETE()
        urlPath("/api/v1/categories/4f47cd20-9fa7-4fdc-973c-97d9689ec668")
    }
    response {
        status 204
    }
}