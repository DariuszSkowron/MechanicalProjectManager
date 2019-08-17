package com.skowrondariusz.mechanicalprojectmanager.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProcessedPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String drawingNumber;
    private String material;
    private String mainProcess;
    private String manufacturer;
    private boolean partFinished;
    private Date readinessOfPart;
    @ManyToOne(fetch = FetchType.LAZY)
    private MechanicalProcessing mechanicalProcessing;


    protected ProcessedPart() {
        this.readinessOfPart = new Date();
    }

    public ProcessedPart(String drawingNumber, String material, String mainProcess, String manufacturer, MechanicalProcessing mechanicalProcessing) {
        this();
        this.drawingNumber = drawingNumber;
        this.material = material;
        this.mainProcess = mainProcess;
        this.manufacturer = manufacturer;
        this.mechanicalProcessing = mechanicalProcessing;
    }

    public MechanicalProcessing getMechanicalProcessing() {
        return mechanicalProcessing;
    }

    public void setMechanicalProcessing(MechanicalProcessing mechanicalProcessing) {
        this.mechanicalProcessing = mechanicalProcessing;
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

    public Date getReadinessOfPart() {
        return readinessOfPart;
    }

    public void setReadinessOfPart(Date readinessOfPart) {
        this.readinessOfPart = readinessOfPart;
    }

    public boolean isPartFinished() {
        return partFinished;
    }

    public void setPartFinished(boolean partFinished) {
        this.partFinished = partFinished;
    }
}
