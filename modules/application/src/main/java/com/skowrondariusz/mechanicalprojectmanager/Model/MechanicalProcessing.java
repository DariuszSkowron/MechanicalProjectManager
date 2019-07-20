package com.skowrondariusz.mechanicalprojectmanager.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class MechanicalProcessing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mechanicalprocessing", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProcessedPart> processedParts;

    public MechanicalProcessing(String name) {
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

}
