package id.co.indivara.jdt12.carrental.controller;

import id.co.indivara.jdt12.carrental.entity.Driver;
import id.co.indivara.jdt12.carrental.entity.Rental;
import id.co.indivara.jdt12.carrental.response.ResponseMessage;
import id.co.indivara.jdt12.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {
    @Autowired
    RentalService rentalService;
    @PostMapping("/rental")
    public Rental createRental(@RequestBody Rental rental) throws Exception{
        rentalService.createRental(rental);
        return rental;
    }
    @GetMapping("/driver/schedule/{rentId}")
    public Object viewSchedule(@PathVariable String rentId, @RequestBody Driver driver) throws Exception{
        Object rental = null;
        try {
            rental = rentalService.viewSchedule(rentId,driver);
        }catch (Exception ex){
            rental = new ResponseMessage(404, ex.getMessage());
        }
        return rental;
    }
    @GetMapping("/viewtranstatus")
    public List<Rental> getAllTranStatus(@RequestParam String rentStatus){
        return rentalService.getAllTransStatus(rentStatus);
    }
    @GetMapping("/viewalltrans")
    public List<Rental> getAllTransaction(){
        return rentalService.getAllTransaction();
    }
}
