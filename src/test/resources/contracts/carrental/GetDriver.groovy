package contracts.carrental
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
Contract.make {
    description "Get a Driver"
    request {
        url "/drivers"
        method HttpMethod.GET.toString()
        body([
                'json-string'
        ])
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body( "Testing" )
    }
}

