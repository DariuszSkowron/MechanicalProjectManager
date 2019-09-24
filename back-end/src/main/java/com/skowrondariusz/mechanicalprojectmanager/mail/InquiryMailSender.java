package com.skowrondariusz.mechanicalprojectmanager.mail;

import com.skowrondariusz.mechanicalprojectmanager.model.Invoice;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InquiryMailSender implements InquirySender  {
    private JavaMailSenderImpl mailSender;


public InquiryMailSender(Environment environment){
    mailSender = new JavaMailSenderImpl();
    mailSender.setHost(environment.getProperty("spring.mail.host"));
    mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
    mailSender.setUsername(environment.getProperty("spring.mail.username"));
    mailSender.setPassword(environment.getProperty("spring.mail.password"));
}


    @Override
    public void sendInquiry(String from, String to, String title, List<String> invoice) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);

        List<String> invoiceList = new ArrayList<>(invoice);
        message.setText(invoiceList.toString());

    }
}

