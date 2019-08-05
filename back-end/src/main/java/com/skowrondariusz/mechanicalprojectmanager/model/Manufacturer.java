package com.skowrondariusz.mechanicalprojectmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommercialPart> commercialParts;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "manufacturer", cascade = CascadeType.ALL)
    @JsonIgnore
    private SalesRepresentative salesRepresentative;


    public Manufacturer() {
    }

    public Manufacturer(List<CommercialPart> commercialParts, SalesRepresentative salesRepresentative) {
        this.commercialParts = commercialParts;
        this.salesRepresentative = salesRepresentative;
    }
    
    private Manufacturer(List<CommercialPart> commercialParts)
    {
        this.commercialParts = commercialParts;
    }


    public Manufacturer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public SalesRepresentative getSalesRepresentative() {
        return salesRepresentative;
    }

    public void setSalesRepresentative(SalesRepresentative salesRepresentative) {
        this.salesRepresentative = salesRepresentative;
    }
}
