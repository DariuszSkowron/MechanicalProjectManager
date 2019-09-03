package com.skowrondariusz.mechanicalprojectmanager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
public class PartsOrder
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partsOrder", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommercialPart> commercialParts;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Project project;



    public PartsOrder() {
    }

    public PartsOrder(String name, List<CommercialPart> commercialParts) {
        this.name = name;
        this.commercialParts = commercialParts;
    }

    public PartsOrder(String name, List<CommercialPart> commercialParts, Project project) {
        this.name = name;
        this.commercialParts = commercialParts;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
