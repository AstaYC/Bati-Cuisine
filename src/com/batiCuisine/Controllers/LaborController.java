package com.batiCuisine.Controllers;

import com.batiCuisine.DAO.Impls.LaborDAOImpl;
import com.batiCuisine.Models.ComponentModel;
import com.batiCuisine.Models.LaborModel;

import java.sql.SQLException;
import java.util.Scanner;

public class LaborController {
    private final LaborDAOImpl laborDAO = new LaborDAOImpl();

    public void CreationLabor(Scanner scanner , int projectId) throws SQLException {
        boolean addLabor = true;
        while(addLabor){
            System.out.println("--- Adding the Labors ---");
            System.out.println("Enter the Labor Name: ");

            String ComponentName = scanner.nextLine();

            // component insert
            ComponentModel component = new ComponentModel(ComponentName , projectId);
            // -----

            System.out.println("Enter the labor type (e.g., Basic Worker, Specialist)");
            String LaborType = scanner.nextLine();
            System.out.println("Enter the hourly rate of this labor (€/h)");
            double HourlyRate = scanner.nextDouble();
            System.out.println("Enter the number of hours worked 'H'");
            double HourlyWorked = scanner.nextDouble();
            System.out.println("Enter the material quality coefficient (1.0 = standard, > 1.0 = high quality)");
            double MaterialQuality = scanner.nextDouble();
            scanner.nextLine();

            LaborModel labor  = new LaborModel(HourlyRate,HourlyWorked,MaterialQuality);
            laborDAO.insertLabor(labor , component);

            System.out.println("Labor added successfully!");
            System.out.println("Do you want to add another labor? (y/n):");
            String answer = scanner.nextLine();

            if(!answer.equalsIgnoreCase("y")){
                addLabor = false;
            }
        }


    }
}
