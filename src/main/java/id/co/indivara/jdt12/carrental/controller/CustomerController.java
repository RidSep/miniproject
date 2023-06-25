package id.co.indivara.jdt12.carrental.controller;

import id.co.indivara.jdt12.carrental.entity.Customer;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import id.co.indivara.jdt12.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/customer")
    public Customer  createCustomer(@RequestBody Customer customer){
       return customerService.createCustomer(customer);
    }
    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @GetMapping("/customer/{customerId}")
    public Customer findByIdCustomer(@PathVariable("customerId") String customerId){
        return customerService.findByIdCustomer(customerId);
    }
    @PutMapping("/customer/{customerId}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("customerId") String customerId){
        return customerService.updateCustomer(customer,customerId);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseMessage deleteCustomer (@PathVariable("customerId") String customerId){
        return customerService.deleteCustomer(customerId);
    }
}
