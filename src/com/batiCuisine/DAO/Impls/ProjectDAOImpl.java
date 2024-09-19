package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.ProjectDAO;
import com.batiCuisine.Models.ProjectModel;
import com.batiCuisine.Utils.DatabaseConnectionManager;


import java.sql.*;

public class ProjectDAOImpl implements ProjectDAO {
    @Override
    public void insertProject(ProjectModel project) throws SQLException {
        String query = "INSERT INTO project (name, profitmerge, totalcost, projectstatus, customer_id) VALUES (?, ? , ? , ? , ?)";

            try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, project.getName());
            preparedStatement.setDouble(2, project.getProfitmerge());
            preparedStatement.setDouble(3, project.getTotalcost());
            preparedStatement.setString(4, project.getProjectstatus());
            preparedStatement.setInt(5, project.getCustomer_id());
            preparedStatement.executeUpdate();
        }

    }
}
