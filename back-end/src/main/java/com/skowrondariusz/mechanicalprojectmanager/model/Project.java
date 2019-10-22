package com.skowrondariusz.mechanicalprojectmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "project_number", unique = true)
    private int projectNumber;

    private long budget;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private MechanicalProcessing mechanicalProcessing;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL)
    private PartsOrder partsOrder;


    private Date projectStartDate;
    private Date projectEndDate;
    private Date projectAssemblingDate;


    public Project() {
    }



    public Project(String name, int projectNumber, long budget, MechanicalProcessing mechanicalProcessing, PartsOrder partsOrder) {
        this.name = name;
        this.projectNumber = projectNumber;
        this.budget = budget;
        this.mechanicalProcessing = mechanicalProcessing;
        this.partsOrder = partsOrder;
    }

    public Project(String name, int projectNumber, long budget, MechanicalProcessing mechanicalProcessing, PartsOrder partsOrder, Date projectStartDate, Date projectEndDate, Date projectAssemblingDate) {
        this.name = name;
        this.projectNumber = projectNumber;
        this.budget = budget;
        this.mechanicalProcessing = mechanicalProcessing;
        this.partsOrder = partsOrder;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.projectAssemblingDate = projectAssemblingDate;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public Date getProjectAssemblingDate() {
        return projectAssemblingDate;
    }

    public void setProjectAssemblingDate(Date projectAssemblingDate) {
        this.projectAssemblingDate = projectAssemblingDate;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MechanicalProcessing getMechanicalProcessing() {
        return mechanicalProcessing;
    }

    public void setMechanicalProcessing(MechanicalProcessing mechanicalProcessing) {
        this.mechanicalProcessing = mechanicalProcessing;
    }

    public PartsOrder getPartsOrder() {
        return partsOrder;
    }

    public void setPartsOrder(PartsOrder partsOrder) {
        this.partsOrder = partsOrder;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectNumber=" + projectNumber +
                ", budget=" + budget +
                ", mechanicalProcessing=" + mechanicalProcessing +
                ", partsOrder=" + partsOrder +
                ", projectStartDate=" + projectStartDate +
                ", projectEndDate=" + projectEndDate +
                ", projectAssemblingDate=" + projectAssemblingDate +
                '}';
    }
}
