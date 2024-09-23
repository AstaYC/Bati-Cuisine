package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.LaborDAO;
import com.batiCuisine.Models.ComponentModel;
import com.batiCuisine.Models.LaborModel;
import com.batiCuisine.Utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaborDAOImpl implements LaborDAO {
    @Override
    public void insertLabor(LaborModel labor , ComponentModel component) throws SQLException {
        String Componentquery = "INSERT INTO component (name, componentType, projectId) VALUES (?, ?, ?)";
        int insertComponentID;
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Componentquery , Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, component.getName());
            preparedStatement.setString(2, "Labor");
            preparedStatement.setInt(3, component.getProjectId());
            preparedStatement.executeUpdate();

            ResultSet getComponentID = preparedStatement.getGeneratedKeys();
            if (getComponentID.next()) {
                insertComponentID = getComponentID.getInt(1);
            }else{
                throw new SQLException("inserting the project failed");
            }
        }

        String LaborQuery = "INSERT INTO labor (id, hourlyRate , hoursWorked , workerProductivity) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LaborQuery)) {

            preparedStatement.setInt(1, insertComponentID);
            preparedStatement.setDouble(2, labor.getHourlyRate());
            preparedStatement.setDouble(3, labor.getHoursWorked());
            preparedStatement.setDouble(4, labor.getWorkerProductivity());
            preparedStatement.executeUpdate();
        }
    }

    public List<LaborModel> getAllLaborForProject(int projectId) throws SQLException {
        String query = "SELECT *, component.name as name FROM labor " +
                "INNER JOIN component ON labor.id = component.id " +
                "WHERE component.projectid = ? " +
                "AND component.componenttype = 'Labor';";


        List<LaborModel> labors = new ArrayList<>();
        try(Connection connection = DatabaseConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                labors.add(new LaborModel(
                        resultSet.getString("name"),
                        resultSet.getDouble("hourlyrate"),
                        resultSet.getDouble("hoursworked"),
                        resultSet.getDouble("workerproductivity")
                ));
            }
        }

        return labors;
    }
}
