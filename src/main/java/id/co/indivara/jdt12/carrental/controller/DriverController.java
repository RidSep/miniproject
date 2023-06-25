package id.co.indivara.jdt12.carrental.controller;

import id.co.indivara.jdt12.carrental.entity.Customer;
import id.co.indivara.jdt12.carrental.entity.Driver;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import id.co.indivara.jdt12.carrental.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {
    @Autowired
    DriverService driverService;

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers(){
        return driverService.getAllDriver();
    }
    @GetMapping("/driver/{driverId}")
    public Driver findByIdCustomer(@PathVariable("driverId") String driverId){
        return driverService.findByIdDriver(driverId);
    }

    @PostMapping("/driver")
    public Driver createDriver(@RequestBody Driver driver){
        return driverService.createDriver(driver);
    }
    @PutMapping("/driver/{driverId}")
    public Driver updateDriver(@RequestBody Driver driver, @PathVariable("driverId") String driverId) throws Exception{
        return driverService.updateDriver(driver,driverId);
    }
    @DeleteMapping("/driver/{driverId}")
    public ResponseMessage deleteDriver(@PathVariable("driverId") String driverId){
        return driverService.deleteDriver(driverId);
    }

}
