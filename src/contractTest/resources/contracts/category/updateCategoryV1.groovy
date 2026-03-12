package contracts.category

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method PUT()
        headers {
            accept 'application/json'
            contentType 'application/json'
        }
        urlPath("/api/v1/categories/4f47cd20-9fa7-4fdc-973c-97d9689ec668")
        body([
            name: value(
                test("Notebook"),
                stub(nonBlank())
            ),
            enabled: value(
                test(true),
                stub(anyBoolean())
            )
        ])
    }
    response {
        status 200
        headers {
            contentType 'application/json'
        }
        body([
            id: fromRequest().path(3),
            name: fromRequest().body('$.name'),
            enabled: fromRequest().body('$.enabled')
        ])
    }
}