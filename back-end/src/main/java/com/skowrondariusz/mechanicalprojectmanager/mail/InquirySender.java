package com.skowrondariusz.mechanicalprojectmanager.mail;

public interface InquirySender {

    void sendInquiry(String from, String name, String invoice);
}
