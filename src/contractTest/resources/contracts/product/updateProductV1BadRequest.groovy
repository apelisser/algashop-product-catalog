package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method PUT()
        headers {
            accept 'application/json'
            contentType 'application/json'
        }
        urlPath("/api/v1/products/d6a660d7-d217-4cab-99f4-fad2d2469c71") {
            body([
                name: " "
            ])
        }
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
                brand: anyNonBlankString(),
                regularPrice: anyNonBlankString(),
                salePrice: anyNonBlankString(),
                enabled: anyNonBlankString(),
                categoryId: anyNonBlankString(),
            ]
        ])
    }

}