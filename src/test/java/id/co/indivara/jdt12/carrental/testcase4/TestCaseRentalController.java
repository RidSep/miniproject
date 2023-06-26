package id.co.indivara.jdt12.carrental.testcase4;

import id.co.indivara.jdt12.carrental.controller.RentalController;
import id.co.indivara.jdt12.carrental.entity.Customer;
import id.co.indivara.jdt12.carrental.entity.Rental;
import id.co.indivara.jdt12.carrental.entity.Vehicle;
import id.co.indivara.jdt12.carrental.mapper.MapperConvertion;
import id.co.indivara.jdt12.carrental.repo.RentalRepository;
import id.co.indivara.jdt12.carrental.service.RentalService;
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
public class TestCaseRentalController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RentalService rentalService;

    @Test
    public void getAllRentalTransaction() throws Exception {
        List<Rental> renatalChecker = rentalService.getAllTransaction();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/viewalltrans")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    List<Rental> rentals = MapperConvertion.getAllData(result.getResponse().getContentAsString(), Rental.class);
                    Assertions.assertNotNull(rentals);
                    Assertions.assertEquals(renatalChecker.get(0).getRentId(), rentals.get(0).getRentId());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentId").isNotEmpty());

    }
    @Test
    public void getTranStatus() throws Exception {
        List<Rental> rental = rentalService.getAllTransStatus("1");
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/viewtranstatus?rentStatus=")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    Rental rentals = MapperConvertion.getData(result.getResponse().getContentAsString(), Rental.class);
                    Assertions.assertNotNull(rentals);
                    Assertions.assertEquals(rental.get(1).getRentStatus(),rentals.getRentStatus());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentStatus").isNotEmpty());
    }

}
