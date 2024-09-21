package com.batiCuisine.Models;

public class ProjectModel {
    private int id;
    private String name;
    private double surfacearea;
    private double profitmerge;
    private double totalcost;
    private String projectstatus;
    private int customer_id;
    private String customer_name;

    public ProjectModel(int id, String name, double surfacearea , double profitmerge, double totalcost, String projectstatus , int customer_id) {
        this.id = id;
        this.name = name;
        this.surfacearea = surfacearea;
        this.profitmerge = profitmerge;
        this.totalcost = totalcost;
        this.projectstatus = projectstatus;
        this.customer_id = customer_id;
    }

    public ProjectModel( String name, double surfacearea, int customer_id) {
        this.name = name;
        this.surfacearea = surfacearea;
        this.customer_id = customer_id;
    }

    public ProjectModel(){};

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

    public double getSurfacearea() {
        return surfacearea;
    }

    public void setSurfacearea(double surfacearea) {
        this.surfacearea = surfacearea;
    }

    public double getProfitmerge() {
        return profitmerge;
    }

    public void setProfitmerge(double profitmerge) {
        this.profitmerge = profitmerge;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public String getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(String projectstatus) {
        this.projectstatus = projectstatus;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name ;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

}
