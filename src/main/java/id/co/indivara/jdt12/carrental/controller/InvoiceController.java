package id.co.indivara.jdt12.carrental.controller;

import id.co.indivara.jdt12.carrental.entity.Invoice;
import id.co.indivara.jdt12.carrental.entity.Rental;
import id.co.indivara.jdt12.carrental.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @PostMapping("/returncar")
    public Invoice returnVehicle(@RequestBody Invoice invoice) throws Exception{
        return invoiceService.returnCar(invoice);
    }
    @GetMapping("/viewtrans")
    public List<Invoice> getAllTransFinish(){
        return invoiceService.getlAllTransFinish();
    }

}
