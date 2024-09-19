package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.MaterialDAO;
import com.batiCuisine.Models.MaterialModel;
import com.batiCuisine.Utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterialDAOImpl implements MaterialDAO {
    @Override
    public void insertMaterial(MaterialModel material) throws SQLException {
        String Componentquery = "INSERT INTO component (name, taxRate, componentType, projectId) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Componentquery)) {

            preparedStatement.setString(1, material.getName());
            preparedStatement.setDouble(2, material.getTaxRate());
            preparedStatement.setString(3, material.getComponentType());
            preparedStatement.setInt(4, material.getProjectId());
            preparedStatement.executeUpdate();
        }

        String Materialquery = "INSERT INTO Material (id, unitCost, quality, transportCost, qualityCoefficient) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Materialquery)) {

            preparedStatement.setInt(1, material.getId());
            preparedStatement.setDouble(2, material.getUnitCost());
            preparedStatement.setDouble(3, material.getQuality());
            preparedStatement.setDouble(4, material.getTransportCost());
            preparedStatement.setDouble(5, material.getQualityCoefficient());
            preparedStatement.executeUpdate();
        }
    }

}
