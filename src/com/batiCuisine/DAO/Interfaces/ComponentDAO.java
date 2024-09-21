package com.batiCuisine.DAO.Interfaces;

import com.batiCuisine.Models.ComponentModel;

import java.awt.*;
import java.sql.SQLException;

public interface ComponentDAO {
    int getTheLastComponentID() throws SQLException;
    void setVatComponent(int id , double vateRate) throws SQLException;
}
