package com.example.status_tracking.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ProductionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trpNumber;

    // Main stages
    private String labCompoundCode;
    private String qualityShrinkage;
    private String design;

    // Mold Manufacturing - Out Source
    private String matPurchase;
    private String preMachining;
    private String grinding;

    // Mold Manufacturing - In House
    private String vmc;
    private String turningVTL;

    // Mold Manufacturing - Out Sourcing
    private String sparkingWire;
    private String heatTreatment;
    private String assembly;

    // Final stages
    private String moldInspectionNpd;
    private String production;
    private String deflashing;

    private LocalDate dateCreated;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrpNumber() {
        return trpNumber;
    }

    public void setTrpNumber(String trpNumber) {
        this.trpNumber = trpNumber;
    }

    public String getLabCompoundCode() {
        return labCompoundCode;
    }

    public void setLabCompoundCode(String labCompoundCode) {
        this.labCompoundCode = labCompoundCode;
    }

    public String getQualityShrinkage() {
        return qualityShrinkage;
    }

    public void setQualityShrinkage(String qualityShrinkage) {
        this.qualityShrinkage = qualityShrinkage;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getMatPurchase() {
        return matPurchase;
    }

    public void setMatPurchase(String matPurchase) {
        this.matPurchase = matPurchase;
    }

    public String getPreMachining() {
        return preMachining;
    }

    public void setPreMachining(String preMachining) {
        this.preMachining = preMachining;
    }

    public String getGrinding() {
        return grinding;
    }

    public void setGrinding(String grinding) {
        this.grinding = grinding;
    }

    public String getVmc() {
        return vmc;
    }

    public void setVmc(String vmc) {
        this.vmc = vmc;
    }

    public String getTurningVTL() {
        return turningVTL;
    }

    public void setTurningVTL(String turningVTL) {
        this.turningVTL = turningVTL;
    }

    public String getSparkingWire() {
        return sparkingWire;
    }

    public void setSparkingWire(String sparkingWire) {
        this.sparkingWire = sparkingWire;
    }

    public String getHeatTreatment() {
        return heatTreatment;
    }

    public void setHeatTreatment(String heatTreatment) {
        this.heatTreatment = heatTreatment;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public String getMoldInspectionNpd() {
        return moldInspectionNpd;
    }

    public void setMoldInspectionNpd(String moldInspectionNpd) {
        this.moldInspectionNpd = moldInspectionNpd;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getDeflashing() {
        return deflashing;
    }

    public void setDeflashing(String deflashing) {
        this.deflashing = deflashing;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
