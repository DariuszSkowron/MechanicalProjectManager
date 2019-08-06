package com.skowrondariusz.mechanicalprojectmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "mechanical_processing", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private MechanicalProcessing mechanicalProcessing;

    public Project() {
    }

    public Project(String name, int projectNumber) {
        this.name = name;
        this.projectNumber = projectNumber;
    }


    public long getId() {
        return id;
    }

    public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
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
