package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {
    @Autowired
    ConsoleRepository consoleRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    SalesTaxRepository salesTaxRepository;
    @Autowired
    ProcessingFeeRepository processingFeeRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TshirtRepository tshirtRepository;

    @Autowired
    public ServiceLayer(ConsoleRepository consoleRepository, InvoiceRepository invoiceRepository, SalesTaxRepository salesTaxRepository,
                        ProcessingFeeRepository processingFeeRepository, GameRepository gameRepository, TshirtRepository tshirtRepository){
        this.consoleRepository = consoleRepository;
        this.invoiceRepository = invoiceRepository;
        this.salesTaxRepository = salesTaxRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.gameRepository = gameRepository;
        this.tshirtRepository = tshirtRepository;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel viewModel){
        Invoice invoice = new Invoice();
        //Fields input by user
        invoice.setName(viewModel.getName());
        invoice.setState(viewModel.getState());
        invoice.setStreet(viewModel.getStreet());
        invoice.setCity(viewModel.getCity());
        invoice.setZipcode(viewModel.getZipcode());
        invoice.setItemType(viewModel.getItemType());
        invoice.setItemId(viewModel.getItemId());
        invoice.setQuantity(viewModel.getQuantity());

        //Calculated Fields
        //Ensure that item exists in database
        if(normalizeItemType(invoice.getItemType()).equals("console")){
            Console console = consoleRepository.findById(invoice.getItemId()).orElseThrow(() ->
                    new NullPointerException("Console does not exist")
            );

            // setting the type to how it is in the repository
            invoice.setItemType("Console");

            //If item exists, set unit price using price from the item's table
            invoice.setUnitPrice(console.getPrice());

            //Ensure that quantity of specific item is available; If so, reduce quantity by quantity provided by user
            if(console.getQuantity() >= invoice.getQuantity()){
                //reduce quantity in DB by invoice quantity
                console.setQuantity(console.getQuantity() - invoice.getQuantity());
                consoleRepository.save(console);  //update console
            }
            else{
                throw new IllegalArgumentException("Not enough consoles in stock");
            }
        }

        else if(normalizeItemType(invoice.getItemType()).equals("game")){
            Game game = gameRepository.findById(invoice.getItemId()).orElseThrow(() ->
                    new NullPointerException("Game does not exist")
            );

            // setting the type to how it is in the repository
            invoice.setItemType("Game");

            //If item exists, set unit price using price from the item's table
            invoice.setUnitPrice(game.getPrice());

            //Ensure that quantity of specific item is available; If so, reduce quantity by quantity provided by user
            if(game.getQuantity() >= invoice.getQuantity()){
                //reduce quantity in DB by invoice quantity
                game.setQuantity(game.getQuantity() - invoice.getQuantity());
                gameRepository.save(game);  //update game
            }
            else{
                throw new IllegalArgumentException("Not enough games in stock");
            }
        }

        else if(normalizeItemType(invoice.getItemType()).equals("tshirt")){
            Tshirt tshirt = tshirtRepository.findById(invoice.getItemId()).orElseThrow(() ->
                            new NullPointerException("T-Shirt does not exist")
            );

            // setting the type to how it is in the repository
            invoice.setItemType("T-Shirt");

            //If item exists, set unit price using price from the item's table
            invoice.setUnitPrice(tshirt.getPrice());

            //Ensure that quantity of specific item is available; If so, reduce quantity by quantity provided by user
            if(tshirt.getQuantity() >= invoice.getQuantity()){
                //reduce quantity in DB by invoice quantity
                tshirt.setQuantity(tshirt.getQuantity() - invoice.getQuantity());
                tshirtRepository.save(tshirt);  //update t-shirt
            }
            else{
                throw new IllegalArgumentException("Not enough T-shirts in stock");
            }
        }

        else{
            throw new IllegalArgumentException("Illegal Argument");
        }

        //Ensure that state entered is valid
        Optional<SalesTaxRate> salesTaxRate = salesTaxRepository.findByState(invoice.getState());
        if(!salesTaxRate.isPresent())
            throw new IllegalArgumentException("e");

        //Calculate subtotal as (unit price * quantity)
        invoice.setSubTotal(invoice.getUnitPrice().multiply(BigDecimal.valueOf(invoice.getQuantity())));

        //Get tax based on state and Calculate sales tax as (sales tax = tax * subtotal)
        invoice.setTax(salesTaxRate.get().getRate().multiply(invoice.getSubTotal()));

        //Select processing fee based on quantity of items;
        //if quantity > 10, processingFee = 15.49 + processingFee from fee table,
        //else, processingFee = processingFee from fee table
        Optional<ProcessingFee> processingFee = processingFeeRepository.findByProductType(invoice.getItemType());
        if(invoice.getQuantity() <= 10)
            invoice.setProcessingFee(processingFee.get().getFee());
        else
            invoice.setProcessingFee(processingFee.get().getFee().add(new BigDecimal("15.49")));

        //Calculate total as total = subtotal + sales tax + processing fee and save
        invoice.setTotal(invoice.getSubTotal().add(invoice.getTax().add(invoice.getProcessingFee())));

        //Save the invoice into the database
        invoice = invoiceRepository.save(invoice);

        return buildInvoiceViewModel(invoice);
    }

    public InvoiceViewModel findInvoice (int id){
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        return invoice.isPresent() ? buildInvoiceViewModel(invoice.get()):null;
    }

    public List<InvoiceViewModel> findAllInvoices(){
        List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();
        List<Invoice> invoices = invoiceRepository.findAll();

        invoices.stream().forEach(t -> invoiceViewModels.add(buildInvoiceViewModel(t)));
        return invoiceViewModels;
    }

    public List<InvoiceViewModel> findAllInvoicesByName(String name){
        List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();
        List<Invoice> invoices = invoiceRepository.findAllByName(name);

        invoices.stream().forEach(t -> invoiceViewModels.add(buildInvoiceViewModel(t)));
        return invoiceViewModels;
    }

    //Helper Methods
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        //Assemble the invoice view model
        InvoiceViewModel avm = new InvoiceViewModel();
        avm.setId(invoice.getId());
        avm.setUnitPrice(invoice.getUnitPrice());
        avm.setTax(invoice.getTax());
        avm.setStreet(invoice.getStreet());
        avm.setZipcode(invoice.getZipcode());
        avm.setState(invoice.getState());
        avm.setSubTotal(invoice.getSubTotal());
        avm.setName(invoice.getName());
        avm.setQuantity(invoice.getQuantity());
        avm.setCity(invoice.getCity());
        avm.setProcessingFee(invoice.getProcessingFee());
        avm.setTotal(invoice.getTotal());
        avm.setItemType(invoice.getItemType());
        avm.setItemId(invoice.getItemId());

        return avm;
    }
    private String normalizeItemType(String type) {
        type = type.replaceAll("[^a-zA-Z0-9]", "");
        type = type.toLowerCase();
        return type;
    }
}
