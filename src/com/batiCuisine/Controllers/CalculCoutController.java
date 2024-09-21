package com.batiCuisine.Controllers;

import com.batiCuisine.DAO.Impls.ComponentDAOImpl;
import com.batiCuisine.DAO.Impls.LaborDAOImpl;
import com.batiCuisine.DAO.Impls.MaterialDAOImpl;
import com.batiCuisine.DAO.Impls.ProjectDAOImpl;
import com.batiCuisine.DAO.Interfaces.ComponentDAO;
import com.batiCuisine.Models.LaborModel;
import com.batiCuisine.Models.MaterialModel;
import com.batiCuisine.Models.ProjectModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculCoutController {
    private final ComponentDAOImpl componentDAO = new ComponentDAOImpl();
    private final ProjectDAOImpl projectDAO = new ProjectDAOImpl();
    private final MaterialDAOImpl materialDAO = new MaterialDAOImpl();
    private final LaborDAOImpl laborDAO = new LaborDAOImpl();

    public void CalculateCout (Scanner scanner) throws SQLException {

       System.out.println("--- Total cost calculation ---");
       System.out.println("Do you want to apply VAT to the project? (y/n)");
       String answer = scanner.nextLine();
       int getComponentID;
       double vat = 0.0;
       if (answer.equalsIgnoreCase("y")) {
           System.out.println("Enter the VAT percentage (%)");
            vat = scanner.nextDouble();

            getComponentID = componentDAO.getTheLastComponentID();
            componentDAO.setVatComponent(getComponentID , vat);
       }
       System.out.println("Would you like to apply a profit margin to the project? (y/n):");
       String marginAnswer = scanner.nextLine();
        double profitMargin = 0.0;
       if (marginAnswer.equalsIgnoreCase("y")) {
           System.out.println("Enter the profit margin percentage (%)");
           profitMargin = scanner.nextDouble();
       }

        System.out.println("Calculation of the cost in progress...");
        System.out.println("--- Calculation Result ---");
        ProjectModel project = projectDAO.getLastProject();

        System.out.println("+---------------------------------------------+");
        System.out.println("|               Project Details               |");
        System.out.println("+---------------------------------------------+");

        System.out.printf("| %-15s : %-30s |\n", "Project Name", project.getName());
        System.out.printf("| %-15s : %-30s |\n", "Client", project.getCustomer_name());
        System.out.printf("| %-15s : %-30.2f m² |\n", "Surface", project.getSurfacearea());

        System.out.println("+---------------------------------------------+");
        System.out.println("--- Cost Details ---");
        System.out.println("1. Materials :");

        List<MaterialModel> materials = materialDAO.getAllMaterialForProject(project.getId());
        double materialCost;
        double totalMaterialCost = 0.0;
        for (MaterialModel material : materials) {
            materialCost = (material.getQuantity() * material.getUnitCost()) * material.getQualityCoefficient() + material.getTransportCost();
            System.out.printf("- %s : %.2f € (Quantity : %.2f m², UnitCost : %.2f €/m², Quality Coefficient : %.2f, Transport Cost : %.2f €)%n",
                    material.getName(),
                    materialCost,
                    material.getQuantity(),
                    material.getUnitCost(),
                    material.getQualityCoefficient(),
                    material.getTransportCost());

            totalMaterialCost += materialCost;
        }
        double totalMaterialCostVat = ((vat*totalMaterialCost)/100) + totalMaterialCost;

        System.out.printf("**Total cost of materials before VAT : %2f €%n" , totalMaterialCost);
        System.out.printf("**Total cost of materials with VAT (%2f) : €%2f" , vat , totalMaterialCostVat);

        System.out.println("2. Labors :");
        List<LaborModel> labors = laborDAO.getAllLaborForProject(project.getId());

        double laborCost;
        double totalLaborCost = 0.0;
        for (LaborModel labor : labors) {
            laborCost = (labor.getHourlyRate() * labor.getHoursWorked()) * labor.getWorkerProductivity();
            System.out.printf("- %s : %.2f € (Hourly Rate : %.2f m², Hours Worked : %.2f €/m², Worker Productivity : %.2f H)%n",
                    labor.getName(),
                    laborCost,
                    labor.getHourlyRate(),
                    labor.getHoursWorked(),
                    labor.getWorkerProductivity());

            totalLaborCost += laborCost;
        }

        double totalLaborCostVat = ((vat*totalLaborCost)/100) + totalLaborCost;

        System.out.printf("**Total cost of Labors before VAT : %2f €%n" , totalLaborCost);
        System.out.printf("**Total cost of Labors with VAT (%2f) : €%2f" , vat , totalLaborCostVat);

        double marginCost = totalMaterialCostVat + totalLaborCostVat ;
        double marginCostProfit = ((marginCost * profitMargin) / 100 ) + marginCost;

        System.out.printf("3. Total Front Margin Cost : %.2f€%n" , marginCost);
        System.out.printf("4. Profit margin (%.2f) : %.2f€%n" , profitMargin , (marginCost * profitMargin) / 100);
        System.out.printf("**Final total project cost : %.2f€%n" , marginCostProfit);

        projectDAO.setCostMarginProject(project.getId() , profitMargin , marginCostProfit);

    }
}
