package com.batiCuisine.Controllers;

import java.sql.SQLException;
import java.util.Scanner;

public class CalculCoutController {
   public void CalculateCout (Scanner scanner) throws SQLException {
       System.out.println("--- Total cost calculation ---");
       System.out.println("Do you want to apply VAT to the project? (y/n)");
       String answer = scanner.nextLine();
       if (answer.equalsIgnoreCase("y")) {
           System.out.println("Enter the VAT percentage (%)");
           double vat = scanner.nextDouble();
       }
   }
}
