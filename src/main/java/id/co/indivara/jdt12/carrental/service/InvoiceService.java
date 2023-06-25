package id.co.indivara.jdt12.carrental.service;

import id.co.indivara.jdt12.carrental.entity.Invoice;
import id.co.indivara.jdt12.carrental.entity.Rental;
import id.co.indivara.jdt12.carrental.repo.CreateTransaksiRepository;
import id.co.indivara.jdt12.carrental.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class InvoiceService {
    @Autowired
    CreateTransaksiRepository createTransaksiRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    @Transactional
    public Invoice returnCar(Invoice invoice) throws Exception{
        Rental ren = createTransaksiRepository.findById(invoice.getRentId()).orElseThrow(()-> new Exception("Not Found Rental Transaction"));
        ren.setRentStatus(Rental.TranscationStatus.FINISH);
        createTransaksiRepository.save(ren);

        Instant start = ren.getCheckIn();
        Instant end = Instant.now();

        BigDecimal totalHours = new BigDecimal(Duration.between(start,end).toHours());
        invoice.setRental(ren);

        if (ren.getDriverId() !=null){ //Kondisi jika pakai driver
            invoice.setDriverCost(invoice.getRental().getDriver().getCostPerhour().multiply(totalHours));
            invoice.setInitialCost(invoice.getRental().getVehicle().getCostPerhour().multiply(totalHours));
            invoice.setTotalCost(invoice.getDriverCost().add(invoice.getInitialCost()));
        }else {
            invoice.setInitialCost(invoice.getRental().getVehicle().getCostPerhour().multiply(totalHours));
            invoice.setTotalCost(invoice.getInitialCost());
        }
        invoice.setReturnDate(end);

        invoiceRepository.save(invoice);
        return invoice;
    }
    public List<Invoice> getlAllTransFinish(){
        return invoiceRepository.findAll();
    }

}
