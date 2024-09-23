package com.batiCuisine.MainClass;

import java.util.Scanner;
import java.sql.SQLException;
import com.batiCuisine.Controllers.ProjectController;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProjectController projectController = new ProjectController();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do{
            // Display the main menu
            System.out.println("===  Main Menu ===");
            System.out.println("1. Create a new project ");
            System.out.println("2. View existing projects");
            System.out.println("3. Calculate the cost of a project");
            System.out.println("4. To leave");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    projectController.createProject(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }

        }while(choice!=4);

            scanner.close();

    }

}
