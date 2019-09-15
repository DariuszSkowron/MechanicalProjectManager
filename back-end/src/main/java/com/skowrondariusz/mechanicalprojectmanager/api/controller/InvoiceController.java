package com.skowrondariusz.mechanicalprojectmanager.api.controller;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.InvoiceViewModel;
import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.ManufacturerViewModel;
import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.model.Invoice;
import com.skowrondariusz.mechanicalprojectmanager.model.Manufacturer;
import com.skowrondariusz.mechanicalprojectmanager.repository.CommercialPartRepository;
import com.skowrondariusz.mechanicalprojectmanager.repository.InvoiceRepository;
import com.skowrondariusz.mechanicalprojectmanager.utility.Mapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {


    private InvoiceRepository invoiceRepository;
    private CommercialPartRepository commercialPartRepository;
    private Mapper mapper;

    public InvoiceController(InvoiceRepository invoiceRepository, CommercialPartRepository commercialPartRepository, Mapper mapper){
        this.invoiceRepository = invoiceRepository;
        this.commercialPartRepository = commercialPartRepository;
        this.mapper = mapper;
    }

    @PostMapping
    public Invoice save(@RequestBody InvoiceViewModel invoice, BindingResult bindingResult) {

        for (Long commercialPart : invoice.getCommercialParts()) {
            if (commercialPartRepository.getOne(commercialPart).getInvoice() != null){
                throw new ValidationException();
            }
        }

        var invoiceEntity = this.mapper.convertToInvoiceEntity(invoice);

//        if (invoiceEntity.getCommercialParts() != null){
            commercialPartRepository.findAllById(invoice.getCommercialParts()).forEach(commercialPart -> commercialPart.setInvoice(invoiceEntity));
//        }

        this.invoiceRepository.save(invoiceEntity);


       return  invoiceEntity;

    }


}
