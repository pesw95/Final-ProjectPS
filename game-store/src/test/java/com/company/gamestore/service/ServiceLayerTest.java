package com.company.gamestore.service;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.ProcessingFee;
import com.company.gamestore.model.SalesTaxRate;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    ServiceLayer service;
    ConsoleRepository consoleRepository;
    InvoiceRepository invoiceRepository;
    ProcessingFeeRepository processingFeeRepository;
    SalesTaxRepository salesTaxRepository;
    GameRepository gameRepository;
    TshirtRepository tshirtRepository;

    @Before
    public void setUp() throws Exception {
        setUpConsoleRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpProcessingFeeRepositoryMock();
        setUpSalesTaxRepositoryMock();

        service = new ServiceLayer(consoleRepository, invoiceRepository, salesTaxRepository, processingFeeRepository, gameRepository,tshirtRepository);
    }

    private void setUpConsoleRepositoryMock(){
        consoleRepository = mock(ConsoleRepository.class);
        Console console = new Console();
        console.setId(7);
        console.setQuantity(100);
        console.setProcessor("Intel Xeon");
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("3GB RAM");
        console.setPrice(new BigDecimal("14.99"));

        Console console2 = new Console();
        console2.setQuantity(150);
        console2.setProcessor("AMD Ryzen");
        console2.setModel("PS-5");
        console2.setManufacturer("Sony");
        console2.setMemoryAmount("3GB RAM");
        console2.setPrice(new BigDecimal("15.99"));

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);

        doReturn(console).when(consoleRepository).save(console2);
        doReturn(Optional.of(console)).when(consoleRepository).findById(7);
        doReturn(consoleList).when(consoleRepository).findAll();
    }

    private void setUpInvoiceRepositoryMock(){
        invoiceRepository = mock(InvoiceRepository.class);
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setCity("Hampton");
        invoice.setQuantity(10);
        invoice.setName("John");
        invoice.setItemId(7);
        invoice.setState("VA");
        invoice.setItemType("Console");
        invoice.setZipcode("23444");
        invoice.setStreet("Virginia Beach street");
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setUnitPrice(new BigDecimal("14.99"));
        invoice.setTax(new BigDecimal("8.9940"));
        invoice.setSubTotal(new BigDecimal("149.90"));
        invoice.setTotal(new BigDecimal("173.8840"));

        Invoice invoice2 = new Invoice();
        invoice2.setCity("Hampton");
        invoice2.setQuantity(10);
        invoice2.setName("John");
        invoice2.setItemId(7);
        invoice2.setState("VA");
        invoice2.setItemType("Console");
        invoice2.setZipcode("23444");
        invoice2.setStreet("Virginia Beach street");
        invoice2.setProcessingFee(new BigDecimal("14.99"));
        invoice2.setUnitPrice(new BigDecimal("14.99"));
        invoice2.setTax(new BigDecimal("8.9940"));
        invoice2.setSubTotal(new BigDecimal("149.90"));
        invoice2.setTotal(new BigDecimal("173.8840"));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceRepository).save(invoice2);
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(1);
        doReturn(invoiceList).when(invoiceRepository).findAll();
        doReturn(invoiceList).when(invoiceRepository).findAllByName("John");
    }

    private void setUpProcessingFeeRepositoryMock(){
        processingFeeRepository = mock(ProcessingFeeRepository.class);
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setFee(new BigDecimal("14.99"));
        processingFee.setProductType("Console");

        List<ProcessingFee> processingFeeList = new ArrayList<>();
        processingFeeList.add(processingFee);

        doReturn(Optional.of(processingFee)).when(processingFeeRepository).findByProductType("Console");
        doReturn(processingFeeList).when(processingFeeRepository).findAll();
    }

    private void setUpSalesTaxRepositoryMock(){
        salesTaxRepository = mock(SalesTaxRepository.class);

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setRate(new BigDecimal("0.06"));
        salesTaxRate.setState("VA");

        List<SalesTaxRate> salesTaxRateList = new ArrayList<>();
        salesTaxRateList.add(salesTaxRate);

        doReturn(Optional.of(salesTaxRate)).when(salesTaxRepository).findByState("VA");
        doReturn(salesTaxRateList).when(salesTaxRepository).findAll();
    }

    @Test
    public void shouldSaveInvoice() {
        InvoiceViewModel expectedResult = new InvoiceViewModel();
        expectedResult.setId(1);
        expectedResult.setCity("Hampton");
        expectedResult.setQuantity(10);
        expectedResult.setName("John");
        expectedResult.setItemId(7);
        expectedResult.setState("VA");
        expectedResult.setItemType("Console");
        expectedResult.setZipcode("23444");
        expectedResult.setStreet("Virginia Beach street");
        expectedResult.setProcessingFee(new BigDecimal("14.99"));
        expectedResult.setUnitPrice(new BigDecimal("14.99"));
        expectedResult.setTax(new BigDecimal("8.9940"));
        expectedResult.setSubTotal(new BigDecimal("149.90"));
        expectedResult.setTotal(new BigDecimal("173.8840"));

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setCity("Hampton");
        invoiceViewModel.setQuantity(10);
        invoiceViewModel.setName("John");
        invoiceViewModel.setItemId(7);
        invoiceViewModel.setState("VA");
        invoiceViewModel.setItemType("Console");
        invoiceViewModel.setZipcode("23444");
        invoiceViewModel.setStreet("Virginia Beach street");
        invoiceViewModel = service.saveInvoice(invoiceViewModel);

        assertEquals(expectedResult, invoiceViewModel);
    }

    @Test
    public void shouldFindInvoice() {
        InvoiceViewModel toCompare = new InvoiceViewModel();
        toCompare.setId(1);
        toCompare.setCity("Hampton");
        toCompare.setQuantity(10);
        toCompare.setName("John");
        toCompare.setItemId(7);
        toCompare.setState("VA");
        toCompare.setItemType("Console");
        toCompare.setZipcode("23444");
        toCompare.setStreet("Virginia Beach street");
        toCompare.setProcessingFee(new BigDecimal("14.99"));
        toCompare.setUnitPrice(new BigDecimal("14.99"));
        toCompare.setTax(new BigDecimal("8.9940"));
        toCompare.setSubTotal(new BigDecimal("149.90"));
        toCompare.setTotal(new BigDecimal("173.8840"));

        InvoiceViewModel invoiceViewModel = service.findInvoice(1);
        assertEquals(invoiceViewModel, toCompare);
    }

    @Test
    public void shouldFindAllInvoices() {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(1);
        invoiceViewModel.setCity("Hampton");
        invoiceViewModel.setQuantity(10);
        invoiceViewModel.setName("John");
        invoiceViewModel.setItemId(7);
        invoiceViewModel.setState("VA");
        invoiceViewModel.setItemType("Console");
        invoiceViewModel.setZipcode("23444");
        invoiceViewModel.setStreet("Virginia Beach street");
        invoiceViewModel.setProcessingFee(new BigDecimal("14.99"));
        invoiceViewModel.setUnitPrice(new BigDecimal("14.99"));
        invoiceViewModel.setTax(new BigDecimal("8.9940"));
        invoiceViewModel.setSubTotal(new BigDecimal("149.90"));
        invoiceViewModel.setTotal(new BigDecimal("173.8840"));

        List<InvoiceViewModel> invoiceViewModels = service.findAllInvoices();

        Assert.assertEquals(invoiceViewModels.size(), 1);
    }

    @Test
    public void shouldFindAllInvoicesByName() {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(1);
        invoiceViewModel.setCity("Hampton");
        invoiceViewModel.setQuantity(10);
        invoiceViewModel.setName("John");
        invoiceViewModel.setItemId(7);
        invoiceViewModel.setState("VA");
        invoiceViewModel.setItemType("Console");
        invoiceViewModel.setZipcode("23444");
        invoiceViewModel.setStreet("Virginia Beach street");
        invoiceViewModel.setProcessingFee(new BigDecimal("14.99"));
        invoiceViewModel.setUnitPrice(new BigDecimal("14.99"));
        invoiceViewModel.setTax(new BigDecimal("8.9940"));
        invoiceViewModel.setSubTotal(new BigDecimal("149.90"));
        invoiceViewModel.setTotal(new BigDecimal("173.8840"));

        List<InvoiceViewModel> invoiceViewModels = service.findAllInvoicesByName("John");

        Assert.assertEquals(invoiceViewModels.size(), 1);
    }
}