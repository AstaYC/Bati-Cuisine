package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.ComponentDAO;
import com.batiCuisine.Utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponentDAOImpl implements ComponentDAO {
    public void setVatComponent (int id , double vat ) throws SQLException {
        String query = "UPDATE component SET vatrate = ? WHERE projectid = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, vat);
            ps.setInt(2, id);

            int update = ps.executeUpdate();
            if (update > 0) {
                System.out.println("VAT UPDATED");
            }else{
                System.out.println("VAT NOT UPDATED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to update component VAT.", e);
        }
    }

    public int getTheLastComponentID() throws SQLException {
        String query = "Select id from component order by id desc limit 1";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

        }
        return -1 ;
    }
}
