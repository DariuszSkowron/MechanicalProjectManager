package com.skowrondariusz.mechanicalprojectmanager.Model;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

//    @Column(name = "project_number", unique = true)
    private int projectNumber;

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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectNumber='" + "P" + projectNumber + '\'' +
                '}';
    }
}
