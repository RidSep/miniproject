package id.co.indivara.jdt12.carrental.service;

import id.co.indivara.jdt12.carrental.entity.Customer;
import id.co.indivara.jdt12.carrental.entity.Driver;
import id.co.indivara.jdt12.carrental.repo.DriverRepository;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;
    public Driver createDriver(Driver driver){
        return driverRepository.save(driver);
    }
    public Driver findByIdDriver(String driverId){
        return driverRepository.findById(driverId).get();
    }
    public List<Driver> getAllDriver(){
        return driverRepository.findAll();
    }
    public Driver updateDriver(Driver driver, String driverId)throws Exception{
        Driver driv = driverRepository.findById(driverId).orElseThrow(()->new Exception("Driver Not Found"));
        if (Objects.nonNull(driver.getDriverName()) && !"".equalsIgnoreCase(driver.getDriverName())){
            driv.setDriverName(driver.getDriverName());
        }if (Objects.nonNull(driver.getContact()) && !"".equalsIgnoreCase(driver.getContact())) {
            driv.setContact(driver.getContact());
        }if (Objects.nonNull(driver.getAddress()) && !"".equalsIgnoreCase(driver.getAddress())) {
            driv.setAddress(driver.getAddress());
        }if (Objects.nonNull(driver.getCostPerhour())) {
            driv.setCostPerhour(driver.getCostPerhour());
        }
        return driverRepository.save(driv);
    }
    public ResponseMessage deleteDriver(String driverId){
         driverRepository.deleteById(driverId);
         return new ResponseMessage(200,"Berhasil Hapus");
    }

}
