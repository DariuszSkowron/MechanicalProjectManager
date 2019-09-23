package com.skowrondariusz.mechanicalprojectmanager.mail;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

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
    public void sendInquiry(String from, String name, String invoice) {

    }
}
