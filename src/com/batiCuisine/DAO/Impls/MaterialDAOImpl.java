package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.MaterialDAO;
import com.batiCuisine.Models.ComponentModel;
import com.batiCuisine.Models.MaterialModel;
import com.batiCuisine.Utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {
    @Override
    public void insertMaterial(MaterialModel material , ComponentModel component ) throws SQLException {
        String Componentquery = "INSERT INTO component (name, componentType, projectId) VALUES (?, ?, ?)";
        int insertComponentID;
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Componentquery)) {

            preparedStatement.setString(1, component.getName());
            preparedStatement.setString(2, "Material");
            preparedStatement.setInt(3, component.getProjectId());
            preparedStatement.executeUpdate();

            ResultSet getComponentID = preparedStatement.getGeneratedKeys();
            if (getComponentID.next()) {
                insertComponentID = getComponentID.getInt(1);
            }else{
                throw new SQLException("inserting the project failed");
            }
        }

        String Materialquery = "INSERT INTO Material (id, unitCost, quality, transportCost, qualityCoefficient) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Materialquery)) {

             preparedStatement.setInt(1, insertComponentID);
             preparedStatement.setDouble(2, material.getUnitCost());
             preparedStatement.setDouble(3, material.getQuantity());
             preparedStatement.setDouble(4, material.getTransportCost());
             preparedStatement.setDouble(5, material.getQualityCoefficient());
             preparedStatement.executeUpdate();
        }
    }

    public List<MaterialModel> getAllMaterialForProject(int projectId) throws SQLException {
        String query = "SELECT *, component.name as name FROM material " +
                "INNER JOIN component ON component.id = material.id " +
                "WHERE component.projectId = ? " +
                "AND component.componenttype = 'Material'";

        List<MaterialModel> materialList = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                materialList.add(new MaterialModel(
                        resultSet.getString("name"),
                        resultSet.getDouble("unitcost"),
                        resultSet.getDouble("quantity"),
                        resultSet.getDouble("transportcost"),
                        resultSet.getDouble("qualitycoefficient")
                ));
            }
        }

        return materialList;
    }

}
