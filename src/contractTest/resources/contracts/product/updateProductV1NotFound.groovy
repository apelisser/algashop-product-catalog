package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method PUT()
        headers {
            accept 'application/json'
            contentType 'application/json'
        }
        urlPath("/api/v1/products/bdc585c9-b535-469e-be44-208cc918b638")
        body([
            name: "Notebook X99",
            brand: "Deep Diver",
            regularPrice: 2000.00,
            salePrice: 1500.00,
            enabled: true,
            categoryId: "f5a5a5a5-a5a5-a5a5-a5a5-a5a5a5a5a5a5",
            description: "A new notebook model for gaming"
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