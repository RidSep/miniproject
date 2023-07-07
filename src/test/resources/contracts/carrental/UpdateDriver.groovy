package contracts.carrental
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
Contract.make {
    description "Post a Driver"
    request {
        url "/driver/297e9e1388e8f92b0188e902d58e0003"
        method HttpMethod.POST.toString()
        body([
                'json-string'
        ])
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body("""
                {
                    "driverId":"297e9e1388e8f92b0188e902d58e0003",
                    "address":"Bogor",
                    "contact":"08787997733",
                    "costPerhour":20000,
                    "driverName":"Uki"
                }
        """)
    }
}

