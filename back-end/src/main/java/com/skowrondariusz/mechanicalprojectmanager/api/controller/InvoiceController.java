package com.skowrondariusz.mechanicalprojectmanager.api.controller;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.ManufacturerViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.model.Invoice;
import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.repository.CommercialPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.InvoiceRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/invoice")
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {


    private InvoiceRepository invoiceRepository;
    private CommercialPartRepository commercialPartRepository;

    public InvoiceController(InvoiceRepository invoiceRepository, CommercialPartRepository commercialPartRepository){
        this.invoiceRepository = invoiceRepository;
        this.commercialPartRepository = commercialPartRepository;
    }

    @PostMapping
    public Invoice save(@RequestBody Invoice invoice) {
//        Invoice invoiceEntity = new Invoice(invoice.getCommercialParts());


        return this.invoiceRepository.save(invoice);

    }


}
