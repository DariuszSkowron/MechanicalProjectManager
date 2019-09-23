package com.skowrondariusz.mechanicalprojectmanager.repository;


import com.skowrondariusz.mechanicalprojectmanager.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {

    Invoice getSpecifiedInvoice (long id);

}
