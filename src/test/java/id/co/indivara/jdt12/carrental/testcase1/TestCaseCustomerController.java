package id.co.indivara.jdt12.carrental.testcase1;

import id.co.indivara.jdt12.carrental.controller.CustomerController;
import id.co.indivara.jdt12.carrental.entity.Customer;
import id.co.indivara.jdt12.carrental.mapper.MapperConvertion;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import id.co.indivara.jdt12.carrental.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCaseCustomerController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerService customerService;


    @Test
    public void getAllVehicleTest() throws Exception {
        List<Customer> customerChecker = customerService.getAllCustomer();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customers")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    List<Customer> customers = MapperConvertion.getAllData(result.getResponse().getContentAsString(), Customer.class);
                    Assertions.assertNotNull(customers);
                    Assertions.assertEquals(customerChecker.get(0).getUserName(), customers.get(0).getUserName());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerId").isNotEmpty());

    }
    @Test
    public void getCustomerId() throws Exception {
        Customer customer = customerService.findByIdCustomer("297e9e1388e8f92b0188e90019470001");
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/297e9e1388e8f92b0188e90019470001")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    Customer customers = MapperConvertion.getData(result.getResponse().getContentAsString(), Customer.class);
                    Assertions.assertNotNull(customers);
                    Assertions.assertEquals(customer.getUserName(),customers.getUserName());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").isNotEmpty());
    }
    @Test
    public void updateCustomer() throws Exception {
        Customer customer = customerService.findByIdCustomer("297e9e1388e8f92b0188e90019470001");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer/297e9e1388e8f92b0188e90019470001")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperConvertion.toJson(customer))

                )
                .andDo(result -> {
                    Customer customers = MapperConvertion.getData(result.getResponse().getContentAsString(), Customer.class);
                    Assertions.assertNotNull(customers);
                    Assertions.assertEquals(customers.getUserName(),customer.getUserName());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").isNotEmpty());
    }
    @Test
    public void addAndDeleteCustomer() throws Exception {
        Customer customer = Customer.builder()
                .userName("Asep")
                .address("Jonggol")
                .contact("087877763")
                .ktpNumber("2544152773")
                .email("asep@gmail.com")
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperConvertion.toJson(customer))
                )
                .andDo(result -> {
                    Customer customers = MapperConvertion.getData(result.getResponse().getContentAsString(), Customer.class);
                    Assertions.assertNotNull(customers);
                    Assertions.assertEquals(customers.getUserName(),customer.getUserName());
                    deleteByCustomerId(customers.getCustomerId());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").isNotEmpty());
    }
    public void deleteByCustomerId(String customerId) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/"+customerId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    ResponseMessage responseMessage = MapperConvertion.getData(result.getResponse().getContentAsString(), ResponseMessage.class);
                    Assertions.assertNotNull(responseMessage);
                    Assertions.assertEquals("Berhasil Hapus",responseMessage.getMessage());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").isNotEmpty());
    }
}
