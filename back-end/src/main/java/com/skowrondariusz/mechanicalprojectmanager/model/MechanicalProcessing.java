package com.skowrondariusz.mechanicalprojectmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MechanicalProcessing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mechanicalProcessing", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProcessedPart> processedParts;




    protected MechanicalProcessing() {
        this.processedParts = new ArrayList<>();
    }

    public MechanicalProcessing(String name) {
        this();
        this.name = name;
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

    public List<ProcessedPart> getProcessedParts() {
        return processedParts;
    }

    public void setProcessedParts(List<ProcessedPart> processedParts) {
        this.processedParts = processedParts;
    }
}
