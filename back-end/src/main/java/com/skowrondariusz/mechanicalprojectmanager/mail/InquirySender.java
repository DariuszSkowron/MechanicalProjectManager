package com.skowrondariusz.mechanicalprojectmanager.mail;

import com.skowrondariusz.mechanicalprojectmanager.model.Invoice;

public interface InquirySender {

    void sendInquiry(String from, String to, String title, Invoice invoice);
}
