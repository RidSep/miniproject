package id.co.indivara.jdt12.carrental.service;

import id.co.indivara.jdt12.carrental.entity.Customer;
import id.co.indivara.jdt12.carrental.repo.CustomerRepository;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
    public Customer findByIdCustomer(String customerId){
        return customerRepository.findById(customerId).get();
    }
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public ResponseMessage deleteCustomer(String customerId){
        customerRepository.deleteById(customerId);
        return new ResponseMessage(200,"Berhasil Hapus");
    }
    public Customer updateCustomer(Customer customer, String customerId){
        Customer cus = customerRepository.findById(customerId).get();
        if (Objects.nonNull(customer.getUserName()) && !"".equalsIgnoreCase(customer.getUserName())){
            cus.setUserName(customer.getUserName());
        }
        if (Objects.nonNull(customer.getEmail()) && !"".equalsIgnoreCase(customer.getEmail())){
            cus.setEmail(customer.getEmail());
        }
        if (Objects.nonNull(customer.getContact()) && !"".equalsIgnoreCase(customer.getContact())){
            cus.setContact(customer.getContact());
        }
        if (Objects.nonNull(customer.getKtpNumber()) && !"".equalsIgnoreCase(customer.getKtpNumber())){
            cus.setKtpNumber(customer.getKtpNumber());
        }
        if (Objects.nonNull(customer.getAddress()) && !"".equalsIgnoreCase(customer.getAddress())){
            cus.setAddress(customer.getAddress());
        }

        return customerRepository.save(cus);
    }
}
