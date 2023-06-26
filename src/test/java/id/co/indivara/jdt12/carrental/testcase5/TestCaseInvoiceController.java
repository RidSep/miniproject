package id.co.indivara.jdt12.carrental.testcase5;

import id.co.indivara.jdt12.carrental.entity.Invoice;
import id.co.indivara.jdt12.carrental.entity.Rental;
import id.co.indivara.jdt12.carrental.mapper.MapperConvertion;
import id.co.indivara.jdt12.carrental.service.InvoiceService;
import id.co.indivara.jdt12.carrental.service.RentalService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCaseInvoiceController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InvoiceService invoiceService;
//    @Test
//    public void getTranStatus() throws Exception {
//        List<Invoice> invoice = invoiceService.getlAllTransFinish();
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/viewtransfinish")
//                        .accept(MediaType.APPLICATION_JSON)
//                )
//                .andDo(result -> {
//                    Invoice invoices = (Invoice) MapperConvertion.getAllData(result.getResponse().getContentAsString(), Invoice.class);
//                    Assertions.assertNotNull(invoices);
//                    Assertions.assertEquals(invoice.get(0).getInvId(), invoices.getInvId());
//                })
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.invId").isNotEmpty());
//    }
}
