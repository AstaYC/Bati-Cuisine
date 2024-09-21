package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.QuoteDAO;
import com.batiCuisine.Models.QuoteModel;
import com.batiCuisine.Utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class QuoteDAOImpl implements QuoteDAO {

    @Override
    public void insertQuote(QuoteModel quote , String savedQuotesAnswer) throws SQLException {
        String query = "insert into quote (estimatedamount , issuedate , validitydate , isaccepted , projectid) values (?,?,?,?,?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, quote.getEstimatedAmount());
            preparedStatement.setDate(2, java.sql.Date.valueOf(quote.getIssueDate()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(quote.getValidityDate()));

            if(savedQuotesAnswer.equalsIgnoreCase("y")){
                preparedStatement.setString(4, "true");
            }else{
                preparedStatement.setString(4, "false");
            }

            preparedStatement.setInt(5, quote.getProjectId());
            preparedStatement.executeUpdate();
        }
    }
}
