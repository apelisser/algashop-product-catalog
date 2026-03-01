package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        headers {
            accept 'application/json'
            contentType 'application/json'
        }
        urlPath("/api/v1/products") {
            body([
                name: "Notebook X11",
                brand: "Deep Diver",
                regularPrice: 1500.00,
                salePrice: 1000.00,
                enabled: true,
                categoryId: "f5a5a5a5-a5a5-a5a5-a5a5-a5a5a5a5a5a5",
                description: "A gamer notebook"
            ])
        }
    }
    response {
        status 201
        headers {
            contentType 'application/json'
        }
        body([
            id: anyUuid(),
            addedAt: anyIso8601WithOffset(),
            name: "Notebook X11",
            brand: "Deep Diver",
            regularPrice: 1500.00,
            salePrice: 1000.00,
            inStock: false,
            enabled: true,
            category: [
                id: "f5a5a5a5-a5a5-a5a5-a5a5-a5a5a5a5a5a5",
                name: "Notebook"
            ],
            description: "A gamer notebook"
        ])
    }
}