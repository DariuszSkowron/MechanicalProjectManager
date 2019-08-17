package com.skowrondariusz.mechanicalprojectmanager.model;

import javax.persistence.*;

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


//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "mechanical_processing", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private MechanicalProcessing mechanicalProcessing;

    public Project() {
    }

    public Project(String name, int projectNumber) {
        this.name = name;
        this.projectNumber = projectNumber;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public Project(String name, int projectNumber, long budget) {
        this.name = name;
        this.projectNumber = projectNumber;
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

//    public MechanicalProcessing getMechanicalProcessing() {
//        return mechanicalProcessing;
//    }

//    public boolean needProcessing(){
//        return
//    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectNumber='" + "P" + projectNumber + '\'' +
                '}';
    }
}
