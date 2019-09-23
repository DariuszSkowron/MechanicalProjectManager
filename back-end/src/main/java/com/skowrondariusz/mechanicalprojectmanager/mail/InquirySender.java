package com.skowrondariusz.mechanicalprojectmanager.mail;

import com.skowrondariusz.mechanicalprojectmanager.model.Invoice;

import java.util.List;

public interface InquirySender {

    void sendInquiry(String from, String to, String title, List<String> invoice);
}
