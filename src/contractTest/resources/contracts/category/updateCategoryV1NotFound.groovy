package contracts.category

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method PUT()
        headers {
            accept 'application/json'
            contentType 'application/json'
        }
        urlPath("/api/v1/categories/df01c615-d379-4085-905b-a98ef51e5fd9")
        body([
            name: "Notebook",
            enabled: true
        ])
    }
    response {
        status 404
        headers {
            contentType "application/problem+json"
        }
        body([
            instance: fromRequest().path(),
            type: "/errors/not-found",
            title: "Not found"
        ])
    }

}