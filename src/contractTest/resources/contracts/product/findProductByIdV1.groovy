package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        headers {
            accept 'application/json'
        }
        url("/api/v1/products/fffe4676-367b-4015-941a-41c31c3b3d3e")
    }
    response {
        status 200
        headers {
            contentType 'application/json'
        }
        body([
            id: fromRequest().path(3),
            addedAt: anyIso8601WithOffset(),
            name: "Notebook X11",
            brand: "Deep Diver",
            regularPrice: 1500.00,
            salePrice: 1000.00,
            inStock: true,
            enabled: true,
            category: [
                id: anyUuid(),
                name: "Notebook"
            ],
            description: "A gamer notebook",
        ])
    }
}