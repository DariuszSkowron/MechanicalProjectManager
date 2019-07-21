package com.skowrondariusz.mechanicalprojectmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProcessedPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String drawingNumber;
    private String material;
    private String mainProcess;
    private String manufacturer;

    public ProcessedPart(String drawingNumber, String material, String mainProcess) {
        this.drawingNumber = drawingNumber;
        this.material = material;
        this.mainProcess = mainProcess;
    }

    public ProcessedPart(String drawingNumber, String material, String mainProcess, String manufacturer) {
        this.drawingNumber = drawingNumber;
        this.material = material;
        this.mainProcess = mainProcess;
        this.manufacturer = manufacturer;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
