package com.skowrondariusz.mechanicalprojectmanager.api.viewmodel;

import javax.validation.constraints.NotNull;

public class ProcessedPartViewModel {

    private String id;

    @NotNull
    private String drawingNumber;
    private String material;
    private String mainProcess;
    private String manufacturer;
    private boolean partFinished;

    @NotNull
    private String mechanicalProcessingId;

    public String getMechanicalProcessingId() {
        return mechanicalProcessingId;
    }

    public void setMechanicalProcessingId(String mechanicalProcessingId) {
        this.mechanicalProcessingId = mechanicalProcessingId;
    }

    public boolean isPartFinished() {
        return partFinished;
    }

    public void setPartFinished(boolean partFinished) {
        this.partFinished = partFinished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public void setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMainProcess() {
        return mainProcess;
    }

    public void setMainProcess(String mainProcess) {
        this.mainProcess = mainProcess;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
