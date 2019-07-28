package com.skowrondariusz.mechanicalprojectmanager.api.viewmodel;

import javax.validation.constraints.NotNull;

public class PartsOrdersViewModel {

    private String id;

    @NotNull
    private String name;

    private int numberOfParts;

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

    public int getNumberOfParts() {
        return numberOfParts;
    }

    public void setNumberOfParts(int numberOfParts) {
        this.numberOfParts = numberOfParts;
    }
}
