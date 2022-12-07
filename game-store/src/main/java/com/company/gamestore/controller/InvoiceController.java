package com.company.gamestore.controller;

import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    //GET Methods
    @GetMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id){
        return serviceLayer.findInvoice(id);
    }

    @GetMapping("/invoice")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices(){
        return serviceLayer.findAllInvoices();
    }

    @GetMapping("/invoice/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoiceByCustomerName(@PathVariable String name){
        return serviceLayer.findAllInvoicesByName(name);
    }

    //POST Method
    @PostMapping("/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody InvoiceViewModel invoiceViewModel) {
        return serviceLayer.saveInvoice(invoiceViewModel);
    }
}
