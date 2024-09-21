package com.batiCuisine.Models;;
import com.batiCuisine.Models.ComponentModel;

public class LaborModel extends ComponentModel{
    private int componentId;
    private double hourlyRate;
    private double hoursWorked;
    private double workerProductivity;

    public LaborModel( double hourlyRate, double hoursWorked, double workerProductivity) {
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.workerProductivity = workerProductivity;
    }

    public LaborModel(String name , double hourlyRate, double hoursWorked, double workerProductivity) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.workerProductivity = workerProductivity;
    }


    public int componentId() {
        return componentId;
    }

    public void componentId(int componentId) {
        this.componentId = componentId;
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

}
