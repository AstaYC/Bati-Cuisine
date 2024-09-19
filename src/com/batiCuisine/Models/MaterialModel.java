package com.batiCuisine.Models;

import java.awt.*;
import com.batiCuisine.Models.ComponentModel;

public class MaterialModel extends ComponentModel {
    private int componentId ;
    private double unitCost;
    private double quality;
    private double transportCost;
    private double qualityCoefficient;

    public MaterialModel(int id, String name, double taxRate, String componentType, int projectId, int componentId , double unitCost, double quality, double transportCost, double qualityCoefficient) {
        super(id, name, taxRate, componentType, projectId);
        this.componentId = componentId;
        this.unitCost = unitCost;
        this.quality = quality;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getQualityCoefficient() {
        return qualityCoefficient;
    }

    public void setQualityCoefficient(double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }
}
