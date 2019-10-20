package com.skowrondariusz.mechanicalprojectmanager.mail;

import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.model.Invoice;
import com.skowrondariusz.mechanicalprojectmanager.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InquiryMailSender implements InquirySender  {
    private JavaMailSenderImpl mailSender;
    private InvoiceRepository invoiceRepository;


public InquiryMailSender(Environment environment, InvoiceRepository invoiceRepository){
    mailSender = new JavaMailSenderImpl();
    mailSender.setHost(environment.getProperty("spring.mail.host"));
    mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
    mailSender.setUsername(environment.getProperty("spring.mail.username"));
    mailSender.setPassword(environment.getProperty("spring.mail.password"));
    this.invoiceRepository = invoiceRepository;
}


    @Override
    public void sendInquiry(String from, String to, String title, String invoiceId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
//        message.setText("test");
        List<CommercialPart> partsList = this.invoiceRepository.getInvoiceById(Long.valueOf(invoiceId)).getCommercialParts();

        String invoice = partsList.stream()
                .map(CommercialPart::getOrderSymbol)
                .collect(Collectors.joining(","));
        message.setText(invoice);


//        List<String> invoiceList = new ArrayList<>(invoice);
//        message.setText(invoiceList.toString());

        this.mailSender.send(message);
    }
}

