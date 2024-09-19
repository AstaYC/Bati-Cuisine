package com.batiCuisine.Models;

public class LaborModel {
    private int id;
    private double hourlyRate;
    private double hoursWorked;
    private double workerProductivity;
    private int componentId;

    public LaborModel(int id, double hourlyRate, double hoursWorked, double workerProductivity, int componentId) {
        this.id = id;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.workerProductivity = workerProductivity;
        this.componentId = componentId;
    }

    // Constructor without id (for creating new labor records)
    public LaborModel(double hourlyRate, double hoursWorked, double workerProductivity, int componentId) {
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.workerProductivity = workerProductivity;
        this.componentId = componentId;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getWorkerProductivity() {
        return workerProductivity;
    }

    public void setWorkerProductivity(double workerProductivity) {
        this.workerProductivity = workerProductivity;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }
}
