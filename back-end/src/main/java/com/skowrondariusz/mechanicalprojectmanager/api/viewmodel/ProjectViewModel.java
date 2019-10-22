package com.skowrondariusz.mechanicalprojectmanager.api.viewmodel;

import com.skowrondariusz.mechanicalprojectmanager.model.MechanicalProcessing;

public class ProjectViewModel {

    private String id;
    private String name;
    private String projectNumber;
    private String budget;
    private String mechanicalProcessing;
    private String partsOrder;
    private String projectAssemblingDate;


    public String getProjectAssemblingDate() {
        return projectAssemblingDate;
    }

    public void setProjectAssemblingDate(String projectAssemblingDate) {
        this.projectAssemblingDate = projectAssemblingDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getMechanicalProcessing() {
        return mechanicalProcessing;
    }

    public void setMechanicalProcessing(String mechanicalProcessing) {
        this.mechanicalProcessing = mechanicalProcessing;
    }

    public String getPartsOrder() {
        return partsOrder;
    }

    public void setPartsOrder(String partsOrder) {
        this.partsOrder = partsOrder;
    }
}
