package com.batiCuisine.DAO.Interfaces;

import com.batiCuisine.Models.CustomerModel;

public interface CustomerDAO {
    void insertCustomer(CustomerModel customerDAO) throws Exception;
    CustomerModel getCustomerByEmail(String email) throws Exception;
}
