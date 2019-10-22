package com.skowrondariusz.mechanicalprojectmanager.mail;

import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.repository.InvoiceRepository;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

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
        List<CommercialPart> partsList = this.invoiceRepository.getInvoiceById(Long.valueOf(invoiceId)).getCommercialParts();

        String invoice = partsList.stream()
                .map(CommercialPart::commercialPartToInquiryString)
                .collect(Collectors.joining("\n"));

        message.setText("Name" + "\t" + "Order Symbol" + "\t" + "Quantity" +"\n" + invoice);
        this.mailSender.send(message);
    }
}

