package com.skowrondariusz.mechanicalprojectmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
    @JsonIgnore
    private List<CommercialPart> commercialParts;


    public Invoice() {
    }

    public Invoice(List<CommercialPart> commercialParts) {
        this.commercialParts = commercialParts;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CommercialPart> getCommercialParts() {
        return commercialParts;
    }

    public void setCommercialParts(List<CommercialPart> commercialParts) {
        this.commercialParts = commercialParts;
    }
}
