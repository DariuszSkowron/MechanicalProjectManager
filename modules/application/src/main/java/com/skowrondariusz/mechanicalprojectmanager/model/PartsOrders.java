package com.skowrondariusz.mechanicalprojectmanager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class PartsOrders {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partsOrders", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommercialPart> commercialParts;


    public PartsOrders() {
    }

    public PartsOrders(String name, List<CommercialPart> commercialParts) {
        this.name = name;
        this.commercialParts = commercialParts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CommercialPart> getCommercialParts() {
        return commercialParts;
    }

    public void setCommercialParts(List<CommercialPart> commercialParts) {
        this.commercialParts = commercialParts;
    }
}
