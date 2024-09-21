package com.batiCuisine.DAO.Impls;

import com.batiCuisine.DAO.Interfaces.CustomerDAO;
import com.batiCuisine.Models.CustomerModel;
import com.batiCuisine.Utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public int insertCustomer(CustomerModel customer) throws SQLException {
        String query = "INSERT INTO customer(name,email,address,phone) VALUES(?,?,?,?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.executeUpdate();

            try (ResultSet getNewInsertCustomerId = preparedStatement.getGeneratedKeys()) {
                if (getNewInsertCustomerId.next()) {
                    return getNewInsertCustomerId.getInt(1);
                }else{
                    throw new SQLException("inserting the customer failed");
                }
            }
        }
    }

    public CustomerModel getCustomerByEmail(String email) throws SQLException {
        String query = "SELECT * FROM customer WHERE email = ?";
        CustomerModel customer = null;
        try (Connection connection = DatabaseConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customer = new CustomerModel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getBoolean("isprofessional")
                );
            }
        }
        return customer;
    }
}
