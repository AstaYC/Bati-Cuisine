package com.batiCuisine.Controllers;

import com.batiCuisine.DAO.Impls.MaterialDAOImpl;
import com.batiCuisine.Models.ComponentModel;
import com.batiCuisine.Models.MaterialModel;

import java.sql.SQLException;
import java.util.Scanner;

public class MaterialController {
    private final MaterialDAOImpl materialDAO = new MaterialDAOImpl();

    public void CreationMaterial(Scanner scanner , int projectId) throws SQLException {
        boolean addMoreMaterial = true;
        while (addMoreMaterial) {
            System.out.println("--- Adding the Material ---");
            System.out.println("Enter the Material Name: ");
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // This clears the newline character after an earlier input
            }
            String ComponentName = scanner.nextLine();

            // component insert
            ComponentModel component = new ComponentModel(ComponentName , projectId);

            // ----- //

            System.out.println("Enter the quantity of this material (in m²))");
            double Quantity = scanner.nextDouble();
            System.out.println("Enter the unit cost of this material (€/m²)");
            double UnitCost = scanner.nextDouble();
            System.out.println("Enter the cost of transporting this material (€)");
            double Cost = scanner.nextDouble();
            System.out.println("Enter the material quality coefficient (1.0 = standard, > 1.0 = high quality)");
            double Quality = scanner.nextDouble();
            scanner.nextLine();

            MaterialModel material = new MaterialModel(UnitCost , Quantity ,  Cost , Quality);
            materialDAO.insertMaterial(material , component);

            System.out.println("Material added successfully!");
            System.out.println("Do you want to add another material? (y/n):");
            String answer = scanner.nextLine();

            if (!answer.equalsIgnoreCase("y")) {
                addMoreMaterial = false;
            }
        }


    }
}
