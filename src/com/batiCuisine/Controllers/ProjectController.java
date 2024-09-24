package com.batiCuisine.Controllers;

import com.batiCuisine.DAO.Impls.CustomerDAOImpl;
import com.batiCuisine.DAO.Impls.LaborDAOImpl;
import com.batiCuisine.DAO.Impls.MaterialDAOImpl;
import com.batiCuisine.DAO.Impls.ProjectDAOImpl;
import com.batiCuisine.DAO.Interfaces.ProjectDAO;
import com.batiCuisine.Models.CustomerModel;
import com.batiCuisine.Models.ProjectModel;

import java.sql.SQLException;
import java.util.List;
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
    private final CalculateProjectContoller calculateProjectContoller = new CalculateProjectContoller();

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
        scanner.nextLine();

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

    public void displayProjects (Scanner scanner) throws SQLException {
        System.out.println("--- Search a Project ---");
        System.out.println("1. Display All Projects");
        System.out.println("2. Choose a project to display");
        int choise = scanner.nextInt();
        scanner.nextLine();

        if (choise == 1) {
           List<ProjectModel> projects = projectDAO.getAllProjects();
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.printf("| %-5s | %-20s | %-15s | %-12s | %-12s | %-15s | %-15s |\n",
                    "ID", "Project Name", "Customer Name", "Surface Area", "Profit Merge", "Total Cost", "Status");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            // Loop through and display each project
            for (ProjectModel project : projects) {
                System.out.printf("| %-5d | %-20s | %-15s | %-12.2f | %-12.2f | %-15.2f | %-15s |\n",
                        project.getId(),
                        project.getName(),
                        project.getCustomer_name(),
                        project.getSurfacearea(),
                        project.getProfitmerge(),
                        project.getTotalcost(),
                        project.getProjectstatus());
            }

            // Closing the table
            System.out.println("+---------------------------------------------------------------------------------------------------+");
        } else if (choise == 2) {
            List<ProjectModel> projects = projectDAO.getAllProjects();

            System.out.println("+---------------------------+");
            System.out.printf("| %-5s | %-20s |\n", "ID", "Project Name");
            System.out.println("+---------------------------+");

            for (ProjectModel project : projects) {
                System.out.printf("| %-5d | %-20s |\n", project.getId(), project.getName());
            }

            System.out.println("+---------------------------+");

            System.out.print("Enter the ID of the project you want to display: ");
            int selectedProjectId = scanner.nextInt();
            scanner.nextLine();

            ProjectModel project = projectDAO.getProjectById(selectedProjectId);
            if (project != null) {
                System.out.println("+---------------------------------------------------+");
                System.out.println("|                   Project Details                 |");
                System.out.println("+---------------------------------------------------+");
                System.out.printf("| %-20s : %-30s |\n", "Project Name", project.getName());
                System.out.printf("| %-20s : %-30s |\n", "Customer Name", project.getCustomer_name());
                System.out.printf("| %-20s : %-30.2f m² |\n", "Surface Area", project.getSurfacearea());
                System.out.printf("| %-20s : %-30.2f € |\n", "Profit Merge", project.getProfitmerge());
                System.out.printf("| %-20s : %-30.2f € |\n", "Total Cost", project.getTotalcost());
                System.out.printf("| %-20s : %-30s |\n", "Project Status", project.getProjectstatus());
                System.out.println("+---------------------------------------------------+");
            } else {
                System.out.println("Project not found.");
            }
        }

    }

    public void CalculateProject(Scanner scanner) throws SQLException {
        List<ProjectModel> projects = projectDAO.getAllProjects();

        System.out.println("+---------------------------+");
        System.out.printf("| %-5s | %-20s |\n", "ID", "Project Name");
        System.out.println("+---------------------------+");

        for (ProjectModel project : projects) {
            System.out.printf("| %-5d | %-20s |\n", project.getId(), project.getName());
        }

        System.out.println("+---------------------------+");
        System.out.println("+---------------------------+\n");

        System.out.print("Enter the ID of the project you want to Calculate: ");
        int selectedProjectId = scanner.nextInt();
        scanner.nextLine();

        calculateProjectContoller.calculateProject(scanner , selectedProjectId);

    }
}

