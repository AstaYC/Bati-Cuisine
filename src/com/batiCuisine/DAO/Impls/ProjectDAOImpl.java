package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.ProjectDAO;
import com.batiCuisine.Models.ProjectModel;
import com.batiCuisine.Utils.DatabaseConnectionManager;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO {
    @Override
    public int insertProject(ProjectModel project) throws SQLException {
        String query = "INSERT INTO project (name, surfacearea , customer_id) VALUES (?, ? , ? )";

            try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, project.getName());
            preparedStatement.setDouble(2, project.getSurfacearea());
            preparedStatement.setInt(3, project.getCustomer_id());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting the project failed, no rows affected.");
            }

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


    public ProjectModel getLastProject() throws SQLException {
        String query = "SELECT *, customer.name AS customer_name FROM project " +
                "JOIN customer ON project.customer_id = customer.id " +
                "ORDER BY project.id DESC " +
                "LIMIT 1";

        ProjectModel project = null ;
        try (Connection connection = DatabaseConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                 project = new ProjectModel(
                         resultSet.getInt("id"),
                         resultSet.getString("name"),
                         resultSet.getDouble("surfacearea"),
                         resultSet.getDouble("profitmerge"),
                         resultSet.getDouble("totalcost"),
                         resultSet.getString("projectstatus"),
                         resultSet.getInt("customer_id")
                 );
                 project.setCustomer_name(resultSet.getString("customer_name"));
            }
        }
        return project;
    }

    @Override
    public void setCostMarginProject(int id , double profitmerge , double totalcost) throws SQLException {
        String query = "Update project set profitmerge = ? , totalcost = ? where id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, profitmerge);
            preparedStatement.setDouble(2, totalcost);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<ProjectModel> getAllProjects() throws SQLException {
        String query = "SELECT * , customer.name as customer_name  FROM project " +
                "Inner JOIN customer " +
                "ON customer.id = project.customer_id ";
        ProjectModel project = null;
        List<ProjectModel> projects = new ArrayList<>();

        try(Connection connection = DatabaseConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                project = new ProjectModel(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("surfacearea"),
                    resultSet.getDouble("profitmerge"),
                    resultSet.getDouble("totalcost"),
                    resultSet.getString("projectstatus"),
                    resultSet.getInt("customer_id")
                );
                    project.setCustomer_name(resultSet.getString("customer_name"));
                    projects.add(project);
            }

        }
        return projects;
    }

    public ProjectModel getProjectById(int id) throws SQLException {
       String query = "Select * , customer.name as customer_name from project " +
               "inner join customer " +
               "on customer.id = project.customer_id " +
               " where project.id = ? ";
       ProjectModel project = null;
       try(Connection connection = DatabaseConnectionManager.getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(query)) {
           preparedStatement.setInt(1, id);
           ResultSet resultSet = preparedStatement.executeQuery();
           if (resultSet.next()) {
               project = new ProjectModel(
                       resultSet.getInt("id"),
                       resultSet.getString("name"),
                       resultSet.getDouble("surfacearea"),
                       resultSet.getDouble("profitmerge"),
                       resultSet.getDouble("totalcost"),
                       resultSet.getString("projectstatus"),
                       resultSet.getInt("customer_id")
               );
               project.setCustomer_name(resultSet.getString("customer_name"));
           }
       }
       return project;
    }

}
