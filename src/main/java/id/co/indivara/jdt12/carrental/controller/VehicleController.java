package id.co.indivara.jdt12.carrental.controller;

import id.co.indivara.jdt12.carrental.entity.Driver;
import id.co.indivara.jdt12.carrental.entity.Vehicle;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import id.co.indivara.jdt12.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicle();
    }
    @GetMapping("/vehicle/{vehicleId}")
    public Vehicle findByIdVehicle(@PathVariable("vehicleId") String vehicleId){
        return vehicleService.findByIdVehicle(vehicleId);
    }
    @PostMapping("/vehicle")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.createVehicle(vehicle);
    }
    @PutMapping("/vehicle/{vehicleId}")
    public Vehicle updateVehicle(@PathVariable("vehicleId") String vehicleId, @RequestBody Vehicle vehicle)throws Exception{
        return vehicleService.updateVehicle(vehicle,vehicleId);
    }
    @DeleteMapping("/vehicle/{vehicleId}")
    public ResponseMessage deleteVehicle(@PathVariable("vehicleId") String vehicleId){
        return vehicleService.deleteVehicle(vehicleId);
    }
}
