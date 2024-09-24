package com.batiCuisine.Controllers;

import com.batiCuisine.DAO.Impls.ComponentDAOImpl;
import com.batiCuisine.DAO.Impls.LaborDAOImpl;
import com.batiCuisine.DAO.Impls.MaterialDAOImpl;
import com.batiCuisine.DAO.Impls.ProjectDAOImpl;
import com.batiCuisine.Models.ComponentModel;
import com.batiCuisine.Models.LaborModel;
import com.batiCuisine.Models.MaterialModel;
import com.batiCuisine.Models.ProjectModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CalculateProjectContoller {
    private final ProjectDAOImpl projectDAO = new ProjectDAOImpl();
    private final MaterialDAOImpl materialDAO = new MaterialDAOImpl();
    private final ComponentDAOImpl componentDAO = new ComponentDAOImpl();
    private final LaborDAOImpl laborDAO = new LaborDAOImpl();

    public void calculateProject(Scanner scanner , int projectId) throws SQLException {
        ProjectModel project = projectDAO.getProjectById(projectId);
        System.out.println("Calculation of the cost in progress...");
        System.out.println();
        System.out.println("+---------------------------------------------+");
        System.out.println("|             Calculation Result              |");
        System.out.println("+---------------------------------------------+");


        System.out.println();
        System.out.println("+---------------------------------------------+");
        System.out.println("|               Project Details               |");
        System.out.println("+---------------------------------------------+");
        System.out.printf("| %-20s : %-30s |\n", "Project Name", project.getName());
        System.out.printf("| %-20s : %-30s |\n", "Client", project.getCustomer_name());
        System.out.printf("| %-20s : %-29.2f m² |\n", "Surface Area", project.getSurfacearea());
        System.out.println("+---------------------------------------------+");

        System.out.println();
        System.out.println("--- Cost Details ---");

        System.out.println("1. Materials:");
        List<MaterialModel> materials = materialDAO.getAllMaterialForProject(projectId);
        ComponentModel component = componentDAO.getComponentById(projectId);
        double vat = component.getTaxRate();
        double materialCost, totalMaterialCost = 0.0;
        for (MaterialModel material : materials) {
            materialCost = (material.getQuantity() * material.getUnitCost()) * material.getQualityCoefficient() + material.getTransportCost();
            System.out.printf("   - %s: %.2f € (Quantity: %.2f m², Unit Cost: %.2f €/m², Quality Coefficient: %.2f, Transport Cost: %.2f €)%n",
                    material.getName(), materialCost, material.getQuantity(), material.getUnitCost(),
                    material.getQualityCoefficient(), material.getTransportCost());
            totalMaterialCost += materialCost;
        }
        double totalMaterialCostVat = ((vat * totalMaterialCost) / 100) + totalMaterialCost;
        System.out.printf("   ** Total cost of materials before VAT: %.2f €%n", totalMaterialCost);
        System.out.printf("   ** Total cost of materials with VAT (%.2f%%): %.2f €%n", vat, totalMaterialCostVat);

        System.out.println();
        System.out.println("2. Labors:");
        List<LaborModel> labors = laborDAO.getAllLaborForProject(projectId);
        double laborCost, totalLaborCost = 0.0;
        for (LaborModel labor : labors) {
            laborCost = (labor.getHourlyRate() * labor.getHoursWorked()) * labor.getWorkerProductivity();
            System.out.printf("   - %s: %.2f € (Hourly Rate: %.2f €/h, Hours Worked: %.2f h, Productivity Factor: %.2f)%n",
                    labor.getName(), laborCost, labor.getHourlyRate(), labor.getHoursWorked(),
                    labor.getWorkerProductivity());
            totalLaborCost += laborCost;
        }
        double totalLaborCostVat = ((vat * totalLaborCost) / 100) + totalLaborCost;
        System.out.printf("   ** Total cost of labors before VAT: %.2f €%n", totalLaborCost);
        System.out.printf("   ** Total cost of labors with VAT (%.2f%%): %.2f €%n", vat, totalLaborCostVat);

        double marginCost = totalMaterialCostVat + totalLaborCostVat;
        double marginProfit = (marginCost * project.getProfitmerge()) / 100;
        double totalProjectCost = marginCost + marginProfit;

        System.out.println();
        System.out.println("3. Final Cost Summary:");
        System.out.printf("   ** Total cost before margin: %.2f €%n", marginCost);
        System.out.printf("   ** Profit margin (%.2f%%): %.2f €%n", project.getProfitmerge(), marginProfit);
        System.out.printf("   ** Final total project cost: %.2f €%n", totalProjectCost);

    }
}
