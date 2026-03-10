package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method DELETE()
        urlPath("/api/v1/products/fffe4676-367b-4015-941a-41c31c3b3d3e")
    }
    response {
        status 204
    }
}