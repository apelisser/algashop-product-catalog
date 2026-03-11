package contracts.category

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        headers {
            accept 'application/json'
        }
        url("/api/v1/categories/4f47cd20-9fa7-4fdc-973c-97d9689ec668")
    }
    response {
        status 200
        headers {
            contentType 'application/json'
        }
        body([
            id: fromRequest().path(3),
            name: "Notebook",
            enabled: true
        ])
    }
}