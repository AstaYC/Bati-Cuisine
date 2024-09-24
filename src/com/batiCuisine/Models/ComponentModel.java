package com.batiCuisine.Models;

public class ComponentModel {
    private int id;
    private String name;
    private double taxRate;
    private String componentType;
    private int projectId;

    public ComponentModel(int id, String name, String componentType, int projectId) {
        this.id = id;
        this.name = name;
        this.componentType = componentType;
        this.projectId = projectId;
    }
    public ComponentModel(int id, String name, double taxRate,  String componentType, int projectId) {
        this.id = id;
        this.taxRate = taxRate;
        this.name = name;
        this.componentType = componentType;
        this.projectId = projectId;
    }

    public ComponentModel( String name) {
        this.name = name;
    }

    public ComponentModel(String name ,  int projectId) {
        this.name = name;
        this.projectId = projectId;
    }

    public ComponentModel() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
