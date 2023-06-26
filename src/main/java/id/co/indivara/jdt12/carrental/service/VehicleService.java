package id.co.indivara.jdt12.carrental.service;

import id.co.indivara.jdt12.carrental.entity.Driver;
import id.co.indivara.jdt12.carrental.entity.Vehicle;
import id.co.indivara.jdt12.carrental.repo.VehicleRepository;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicle(){
        return vehicleRepository.findAll();
    }
    public Vehicle findByIdVehicle(String vehicleId){
        return vehicleRepository.findById(vehicleId).get();
    }
    public Vehicle createVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }
    public Vehicle updateVehicle(Vehicle vehicle, String vehicleId) throws Exception{
        Vehicle vec = vehicleRepository.findById(vehicleId).orElseThrow(()->new Exception("Vehicle Not Found"));
        if (Objects.nonNull(vehicle.getVehicleBrand()) && !"".equalsIgnoreCase(vehicle.getVehicleBrand())){
            vec.setVehicleBrand(vehicle.getVehicleBrand());
        } if (Objects.nonNull(vehicle.getVehicleSeries()) && !"".equalsIgnoreCase(vehicle.getVehicleSeries())) {
            vec.setVehicleSeries(vehicle.getVehicleSeries());
        } if (Objects.nonNull(vehicle.getVehicleType()) && !"".equalsIgnoreCase(vehicle.getVehicleType())) {
            vec.setVehicleType(vehicle.getVehicleType());
        } if (Objects.nonNull(vehicle.getVehicleNo()) && !"".equalsIgnoreCase(vehicle.getVehicleNo())) {
            vec.setVehicleNo(vehicle.getVehicleNo());
        } if (Objects.nonNull(vehicle.getCostPerhour())) {
            vec.setCostPerhour(vehicle.getCostPerhour());
        }
        return vehicleRepository.save(vec);
    }
    public ResponseMessage deleteVehicle(String vehicleId){
        vehicleRepository.deleteById(vehicleId);
        return new ResponseMessage(200,"Berhasil Hapus");
    }
}
