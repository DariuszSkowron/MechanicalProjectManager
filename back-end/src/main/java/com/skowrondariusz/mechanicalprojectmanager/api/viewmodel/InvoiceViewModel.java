package com.skowrondariusz.mechanicalprojectmanager.api.viewmodel;

import java.util.List;

public class InvoiceViewModel {

    private Long id;
    private List<Long> commercialParts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getCommercialParts() {
        return commercialParts;
    }

    public void setCommercialParts(List<Long> commercialParts) {
        this.commercialParts = commercialParts;
    }
}
