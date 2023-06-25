package id.co.indivara.jdt12.carrental.testcase2;

import id.co.indivara.jdt12.carrental.controller.DriverController;
import id.co.indivara.jdt12.carrental.entity.Customer;
import id.co.indivara.jdt12.carrental.entity.Driver;
import id.co.indivara.jdt12.carrental.mapper.MapperConvertion;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import id.co.indivara.jdt12.carrental.service.DriverService;
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

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCaseDriverController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverController driverController;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(driverController).build();
    }

    @Test
    public void getAllDriverTest() throws Exception {
        List<Driver> driverChecker = driverService.getAllDriver();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/drivers")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    List<Driver> drivers = MapperConvertion.getAllData(result.getResponse().getContentAsString(), Driver.class);
                    Assertions.assertNotNull(drivers);
                    Assertions.assertEquals(driverChecker.get(0).getDriverName(), drivers.get(0).getDriverName());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].driverId").isNotEmpty());

    }
    @Test
    public void getDriverId() throws Exception {
        Driver driver = driverService.findByIdDriver("297e9e1388e8f92b0188e904c2ca0005");
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/driver/297e9e1388e8f92b0188e904c2ca0005")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    Driver drivers = MapperConvertion.getData(result.getResponse().getContentAsString(), Driver.class);
                    Assertions.assertNotNull(drivers);
                    Assertions.assertEquals(driver.getDriverName(),drivers.getDriverName());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.driverId").isNotEmpty());
    }
    @Test
    public void updateCustomer() throws Exception {
        Driver driver = driverService.findByIdDriver("297e9e1388e8f92b0188e904c2ca0005");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/driver/297e9e1388e8f92b0188e904c2ca0005")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperConvertion.toJson(driver))

                )
                .andDo(result -> {
                    Driver drivers = MapperConvertion.getData(result.getResponse().getContentAsString(), Driver.class);
                    Assertions.assertNotNull(drivers);
                    Assertions.assertEquals(drivers.getDriverName(),driver.getDriverName());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.driverId").isNotEmpty());
    }
    @Test
    public void addAndDeleteCustomer() throws Exception {
        Driver driver = Driver.builder()
                .driverName("Rendi")
                .address("Jonggol")
                .contact("087877763")
                .costPerhour(BigDecimal.valueOf(20000))
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/driver")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperConvertion.toJson(driver))
                )
                .andDo(result -> {
                    Driver drivers = MapperConvertion.getData(result.getResponse().getContentAsString(), Driver.class);
                    Assertions.assertNotNull(drivers);
                    Assertions.assertEquals(drivers.getDriverName(),driver.getDriverName());
                    deleteByDriverId(drivers.getDriverId());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.driverId").isNotEmpty());
    }
    public void deleteByDriverId(String driverId) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/driver/"+driverId)
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
