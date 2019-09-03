package com.skowrondariusz.mechanicalprojectmanager.api.viewmodel;

import javax.validation.constraints.NotNull;

public class PartsOrderViewModel
{

    private String id;

    @NotNull
    private String name;

    private int numberOfParts;

    private String project;

    private String projectId;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getNumberOfParts() {
        return numberOfParts;
    }

    public void setNumberOfParts(int numberOfParts) {
        this.numberOfParts = numberOfParts;
    }


}
