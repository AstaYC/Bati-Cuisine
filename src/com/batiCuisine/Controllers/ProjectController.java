package com.batiCuisine.Controllers;

import com.batiCuisine.DAO.Impls.CustomerDAOImpl;
import com.batiCuisine.DAO.Impls.LaborDAOImpl;
import com.batiCuisine.DAO.Impls.MaterialDAOImpl;
import com.batiCuisine.DAO.Impls.ProjectDAOImpl;
import com.batiCuisine.DAO.Interfaces.ProjectDAO;
import com.batiCuisine.Models.CustomerModel;
import com.batiCuisine.Models.ProjectModel;

import java.sql.SQLException;
import java.util.Scanner;

public class ProjectController {
    private final CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    private final MaterialDAOImpl materialDAO = new MaterialDAOImpl();
    private final LaborDAOImpl laborDAO = new LaborDAOImpl();
    private final CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
    private final ProjectDAOImpl projectDAO = new ProjectDAOImpl();
    private final MaterialController materialController = new MaterialController();
    private final LaborController laborController = new LaborController();
    private final CalculCoutController calculCoutController = new CalculCoutController();
    private final SaveQuoteController saveQuoteController = new SaveQuoteController();

    public void createProject(Scanner scanner) throws SQLException {
        boolean contunueCustomer = true ;
        int CustomerId = -1;

        System.out.println("-- Search a Customer ? --");
        System.out.println("Do you want to search for an existing customer or add a new one?");
        System.out.println("1.Search a Customer");
        System.out.println("2.ADD a New Customer");

        int choise = scanner.nextInt();
        scanner.nextLine();
        if (choise == 1) {
            while (contunueCustomer) {
                System.out.println("Enter the email of the customer: ");
                String email = scanner.nextLine();
                CustomerModel customer = customerDAO.getCustomerByEmail(email);
                if (customer != null) {
                    CustomerId = customer.getId();
                    System.out.println("Customer found!");
                    System.out.println("Name: " + customer.getName());
                    System.out.println("Address: " + customer.getAddress());
                    System.out.println("Phone Number: " + customer.getPhone());
                } else {
                    System.out.println("Customer not found!");
                    return;
                }
                System.out.println("Would you like to continue with this client? (y/n)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    contunueCustomer = false;
                }
            }
        }else if (choise == 2) {

            System.out.println("---- ADDing New Customer ----");
            System.out.println("Enter the name of the customer: ");
            String name = scanner.nextLine();
            System.out.println("Enter the email of the customer: ");
            String email = scanner.nextLine();
            System.out.println("Enter the address of the customer: ");
            String address = scanner.nextLine();
            System.out.println("Enter the phone number of the customer: ");
            String phone = scanner.nextLine();

            CustomerModel customer = new CustomerModel(name, email, address, phone);
            CustomerId =  customerDAOImpl.insertCustomer(customer);
        }

        System.out.println("---  Creation of a New Project   ---");
        System.out.println("Enter the project name");
        String projectName = scanner.nextLine();
        System.out.println("Enter the kitchen surface area (with m²)" );
        double kitchenSurfaceArea = scanner.nextDouble();

        if (CustomerId == -1){
            System.out.println("Customer ID not found!");
            return;
        }
        ProjectModel project = new ProjectModel(projectName , kitchenSurfaceArea , CustomerId);

        int projectID = projectDAO.insertProject(project);

        // Add material //
        materialController.CreationMaterial(scanner , projectID);
        //-------------------------------//

        // Add Labor //
        laborController.CreationLabor(scanner , projectID);
        // ----------------------------- //

        // Calculate cost //
        calculCoutController.CalculateCout(scanner);
        // ---------------------------- //

        // saving Quotes //
        saveQuoteController.saveQuote(scanner);
        // ---------------------------- //

        System.out.println("--- End of project ---");

    }
}

