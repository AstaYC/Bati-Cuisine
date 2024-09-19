package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.LaborDAO;
import com.batiCuisine.Models.LaborModel;
import com.batiCuisine.Utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LaborDAOImpl implements LaborDAO {
    @Override
    public void insertLabor(LaborModel labor) throws SQLException {
        String Componentquery = "INSERT INTO component (name, taxRate, componentType, projectId) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Componentquery)) {

            preparedStatement.setString(1, labor.getName());
            preparedStatement.setDouble(2, labor.getTaxRate());
            preparedStatement.setString(3, labor.getComponentType());
            preparedStatement.setInt(4, labor.getProjectId());
            preparedStatement.executeUpdate();
        }

        String LaborQuery = "INSERT INTO labor (id, hourlyRate , hoursWorked , workerProductivity) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LaborQuery)) {

            preparedStatement.setInt(1, labor.getId());
            preparedStatement.setDouble(2, labor.getHourlyRate());
            preparedStatement.setDouble(3, labor.getHoursWorked());
            preparedStatement.setDouble(4, labor.getWorkerProductivity());
            preparedStatement.executeUpdate();
        }
    }
}
