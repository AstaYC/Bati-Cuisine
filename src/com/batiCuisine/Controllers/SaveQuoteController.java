package com.batiCuisine.Controllers;

import com.batiCuisine.DAO.Impls.ProjectDAOImpl;
import com.batiCuisine.DAO.Impls.QuoteDAOImpl;
import com.batiCuisine.Models.ProjectModel;
import com.batiCuisine.Models.QuoteModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class SaveQuoteController {
    private final QuoteDAOImpl quoteDAO = new QuoteDAOImpl();
    private final ProjectDAOImpl projectDAO = new ProjectDAOImpl();

    public void saveQuote(Scanner scanner) throws SQLException{
        System.out.println("--- Saving the Quote ---");
        System.out.println("Enter the quote issue date (format: yyyy/mm/dd) :");
        LocalDate issueDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter the quote validity date (format: dd/mm/yyyy):");
        LocalDate validityDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Would you like to save the quote? (y/n)");
        String answer = scanner.nextLine();

        ProjectModel project = projectDAO.getLastProject();

        QuoteModel quote = new QuoteModel(project.getTotalcost() , issueDate , validityDate , project.getId());

        quoteDAO.insertQuote(quote , answer);

        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Saved the Quote successfully!");
        }else{
            System.out.println("The Quote Not Saved!");
        }
    }
}
