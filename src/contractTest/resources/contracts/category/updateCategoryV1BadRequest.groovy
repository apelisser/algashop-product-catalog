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
            name: " "
        ])
    }
    response {
        status 400
        headers {
            contentType 'application/problem+json'
        }
        body([
            instance: fromRequest().path(),
            type: "/errors/invalid-fields",
            title: "Invalid fields",
            detail: "One or more fields are invalid",
            fields: [
                name: anyNonBlankString(),
                enabled: anyNonBlankString()
            ]
        ])
    }

}