package com.company.gamestore.repository;

import com.company.gamestore.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Before
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
    }

    @Test
    public void addGetInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCity("Hampton");
        invoice.setQuantity(3);
        invoice.setName("John");
        invoice.setItemId(1);
        invoice.setState("VA");
        invoice.setItemType("Game");
        invoice.setZipcode("23444");
        invoice.setStreet("Virginia Beach street");
        invoice.setProcessingFee(new BigDecimal("4.11"));
        invoice.setUnitPrice(new BigDecimal("140.99"));
        invoice.setTax(new BigDecimal("3.22"));
        invoice.setSubTotal(new BigDecimal("150.55"));
        invoice.setTotal(new BigDecimal("155.71"));

        invoice = invoiceRepository.save(invoice);
        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getId());

        assertEquals(invoice1.get(), invoice);

        invoice1 = invoiceRepository.findById(invoice.getId());

        assertTrue(invoice1.isPresent());
    }

    @Test
    public void getAllInvoices() {
        Invoice invoice = new Invoice();
        invoice.setCity("Hampton");
        invoice.setQuantity(3);
        invoice.setName("John");
        invoice.setItemId(1);
        invoice.setState("VA");
        invoice.setItemType("Game");
        invoice.setZipcode("23444");
        invoice.setStreet("Virginia Beach street");
        invoice.setProcessingFee(new BigDecimal("4.11"));
        invoice.setUnitPrice(new BigDecimal("140.99"));
        invoice.setTax(new BigDecimal("3.22"));
        invoice.setSubTotal(new BigDecimal("150.55"));
        invoice.setTotal(new BigDecimal("155.71"));
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setCity("Hampton");
        invoice.setQuantity(3);
        invoice.setName("John");
        invoice.setItemId(1);
        invoice.setState("VA");
        invoice.setItemType("Game");
        invoice.setZipcode("23444");
        invoice.setStreet("Virginia Beach street");
        invoice.setProcessingFee(new BigDecimal("4.11"));
        invoice.setUnitPrice(new BigDecimal("140.99"));
        invoice.setTax(new BigDecimal("3.22"));
        invoice.setSubTotal(new BigDecimal("150.55"));
        invoice.setTotal(new BigDecimal("155.71"));
        invoice = invoiceRepository.save(invoice);

        List<Invoice> aList = invoiceRepository.findAll();

        assertEquals(aList.size(), 2);
    }

    @Test
    public void findInvoicesByName() {
        Invoice invoice = new Invoice();
        invoice.setCity("Hampton");
        invoice.setQuantity(3);
        invoice.setName("John");
        invoice.setItemId(1);
        invoice.setState("VA");
        invoice.setItemType("Game");
        invoice.setZipcode("23444");
        invoice.setStreet("Virginia Beach street");
        invoice.setProcessingFee(new BigDecimal("4.11"));
        invoice.setUnitPrice(new BigDecimal("140.99"));
        invoice.setTax(new BigDecimal("3.22"));
        invoice.setSubTotal(new BigDecimal("150.55"));
        invoice.setTotal(new BigDecimal("155.71"));
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setCity("Hampton");
        invoice.setQuantity(3);
        invoice.setName("John");
        invoice.setItemId(1);
        invoice.setState("VA");
        invoice.setItemType("Game");
        invoice.setZipcode("23444");
        invoice.setStreet("Virginia Beach street");
        invoice.setProcessingFee(new BigDecimal("4.11"));
        invoice.setUnitPrice(new BigDecimal("140.99"));
        invoice.setTax(new BigDecimal("3.22"));
        invoice.setSubTotal(new BigDecimal("150.55"));
        invoice.setTotal(new BigDecimal("155.71"));
        invoice = invoiceRepository.save(invoice);

        List<Invoice> invoices = invoiceRepository.findAllByName("John");
        assertEquals(invoices.size(), 2);
    }
}
