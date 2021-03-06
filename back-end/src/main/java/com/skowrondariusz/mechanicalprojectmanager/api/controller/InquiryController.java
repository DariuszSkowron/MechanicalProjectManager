package com.skowrondariusz.mechanicalprojectmanager.api.controller;


import com.skowrondariusz.mechanicalprojectmanager.api.viewmodel.InquiryViewModel;
import com.skowrondariusz.mechanicalprojectmanager.mail.InquirySender;
import com.skowrondariusz.mechanicalprojectmanager.repository.InvoiceRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/inquiry")
public class InquiryController {

    private InquirySender inquirySender;
    private InvoiceRepository invoiceRepository;

    public InquiryController(InquirySender inquirySender, InvoiceRepository invoiceRepository) {
        this.inquirySender = inquirySender;
        this.invoiceRepository = invoiceRepository;
    }

    @PostMapping
    public void sendInquiry(@RequestBody InquiryViewModel inquiryViewModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Could'nt send inquiry, check your parameters and try again");
        }

        this.inquirySender.sendInquiry(
                inquiryViewModel.getFrom(),
                inquiryViewModel.getSendTo(),
                inquiryViewModel.getTitle(),
                inquiryViewModel.getInvoiceId());

    }
}
