package com.skowrondariusz.mechanicalprojectmanager.mail;


public interface InquirySender {

    void sendInquiry(String from, String to, String title, String invoiceId);
}
