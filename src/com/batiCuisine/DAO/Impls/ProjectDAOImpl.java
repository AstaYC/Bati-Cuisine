package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.ProjectDAO;
import com.batiCuisine.Models.ProjectModel;
import com.batiCuisine.Utils.DatabaseConnectionManager;


import java.sql.*;

public class ProjectDAOImpl implements ProjectDAO {
    @Override
    public int insertProject(ProjectModel project) throws SQLException {
        String query = "INSERT INTO project (name, surfacearea , customer_id) VALUES (?, ? , ? )";

            try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, project.getName());
            preparedStatement.setDouble(2, project.getSurfacearea());
            preparedStatement.setInt(3, project.getCustomer_id());
            preparedStatement.executeUpdate();

            try (ResultSet getNewInsertId = preparedStatement.getGeneratedKeys()){
                if(getNewInsertId.next()){
                    return  getNewInsertId.getInt(1);
                }else {
                     throw new SQLException("inserting the project failed");
                }
            }

        }

    }

    public void updateProject(ProjectModel project) {
        String query = "UPDATE projects SET profit_merge = ?, total_cost = ? WHERE id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, project.getProfitmerge());
            preparedStatement.setDouble(2, project.getTotalcost());
            preparedStatement.setInt(3, project.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Project updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
