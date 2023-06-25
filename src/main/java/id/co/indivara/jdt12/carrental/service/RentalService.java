package id.co.indivara.jdt12.carrental.service;

import id.co.indivara.jdt12.carrental.entity.*;
import id.co.indivara.jdt12.carrental.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class RentalService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    CreateTransaksiRepository createTransaksiRepository;
    @Transactional
    public Rental createRental(Rental rental) throws Exception {
        Vehicle vehicle = vehicleRepository.findById(rental.getVehicleId()).orElseThrow(()-> new Exception("Vehicle Not Found"));
        String isVehicleAvail = createTransaksiRepository.findVehicleAvailbility(vehicle.getVehicleId());
        if (isVehicleAvail !=null && isVehicleAvail.equalsIgnoreCase("no")) {
            throw new Exception("Vehicle Not Available");
        }
        if (rental.getDriverId()!=null){
            checkDriver(rental);
        }

        Customer customer = customerRepository.findById(rental.getCustomerId()).orElseThrow(()-> new Exception("Customer Not Found"));
        rental.setVehicle(vehicle);
        rental.setCustomer(customer);
        rental.setCheckIn(rental.getCheckIn());
        rental.setCheckOut(rental.getCheckOut());
        if (Instant.now().isBefore(rental.getCheckIn())){
            rental.setRentStatus(Rental.TranscationStatus.RENT);
        }else {
            rental.setRentStatus(Rental.TranscationStatus.ON_GOING);
        }
        createTransaksiRepository.save(rental);

        return rental;
    }
    public void checkDriver (Rental rental) throws Exception{
        Driver driver = driverRepository.findById(rental.getDriverId()).orElseThrow(()->new Exception("Driver Not Found"));
        String isDriverAvail = createTransaksiRepository.findDriverAvailbility(driver.getDriverId());
        if (isDriverAvail !=null && isDriverAvail.equalsIgnoreCase("no")){
            throw new Exception("Driver Not Available");
        }
        rental.setDriver(driver);
    }
    public Rental viewSchedule(String rentId, Driver driver) throws Exception{
        Rental rental = createTransaksiRepository.findById(rentId).orElseThrow(()->new Exception("Transaction Not Found"));
        Driver dri = driverRepository.findById(driver.getDriverId()).orElseThrow(()->new Exception("Driver Not Found"));
        String driverShceduleAvail = createTransaksiRepository.findDriverAvailbility(dri.getDriverId());
        if (driverShceduleAvail != null && driverShceduleAvail.equalsIgnoreCase("yes")){
            throw new Exception("Shcedule Has Been End");
        } else if (driverShceduleAvail == null) {
            throw new Exception("Shcedule Not Found");
        }
        return rental;
    }
    public List<Rental> getAllTransaction(){
        return createTransaksiRepository.findAll();
    }
    public List<Rental> getAllTransStatus(String rentStatus){
        return createTransaksiRepository.findByRentStatus(rentStatus);
    }
}
