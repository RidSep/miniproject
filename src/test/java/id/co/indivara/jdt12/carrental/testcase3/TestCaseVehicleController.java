package id.co.indivara.jdt12.carrental.testcase3;

import id.co.indivara.jdt12.carrental.controller.VehicleController;
import id.co.indivara.jdt12.carrental.entity.Vehicle;
import id.co.indivara.jdt12.carrental.mapper.MapperConvertion;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import id.co.indivara.jdt12.carrental.service.VehicleService;
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
public class TestCaseVehicleController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VehicleService vehicleService;

    @Test
    public void getAllVehicleTest() throws Exception {
        List<Vehicle> vehicleChecker = vehicleService.getAllVehicle();
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/vehicles")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    List<Vehicle> vehicles = MapperConvertion.getAllData(result.getResponse().getContentAsString(), Vehicle.class);
                    Assertions.assertNotNull(vehicles);
                    Assertions.assertEquals(vehicleChecker.get(0).getVehicleBrand(), vehicles.get(0).getVehicleBrand());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicleId").isNotEmpty());

    }
    @Test
    public void getVehicleId() throws Exception {
        Vehicle vehicle = vehicleService.findByIdVehicle("297e9e1388e8f92b0188e90a6f710008");
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/vehicle/297e9e1388e8f92b0188e90a6f710008")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(result -> {
                    Vehicle vehicles = MapperConvertion.getData(result.getResponse().getContentAsString(), Vehicle.class);
                    Assertions.assertNotNull(vehicles);
                    Assertions.assertEquals(vehicle.getVehicleBrand(),vehicles.getVehicleBrand());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId").isNotEmpty());
    }
    @Test
    public void updateCustomer() throws Exception {
        Vehicle vehicle = vehicleService.findByIdVehicle("297e9e1388e8f92b0188e90a6f710008");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/vehicle/297e9e1388e8f92b0188e90a6f710008")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperConvertion.toJson(vehicle))

                )
                .andDo(result -> {
                    Vehicle vehicles = MapperConvertion.getData(result.getResponse().getContentAsString(), Vehicle.class);
                    Assertions.assertNotNull(vehicles);
                    Assertions.assertEquals(vehicles.getVehicleBrand(),vehicle.getVehicleBrand());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId").isNotEmpty());
    }
    @Test
    public void addAndDeleteCustomer() throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .vehicleBrand("Kijang")
                .vehicleNo("B-9281-AU")
                .vehicleType("Manual")
                .vehicleSeries("Jadul")
                .costPerhour(BigDecimal.valueOf(20000))
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/vehicle")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MapperConvertion.toJson(vehicle))
                )
                .andDo(result -> {
                    Vehicle vehicles = MapperConvertion.getData(result.getResponse().getContentAsString(), Vehicle.class);
                    Assertions.assertNotNull(vehicles);
                    Assertions.assertEquals(vehicles.getVehicleBrand(),vehicle.getVehicleBrand());
                    deleteByDriverId(vehicles.getVehicleId());
                })
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId").isNotEmpty());
    }
    public void deleteByDriverId(String vehicleId) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/vehicle/"+vehicleId)
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
