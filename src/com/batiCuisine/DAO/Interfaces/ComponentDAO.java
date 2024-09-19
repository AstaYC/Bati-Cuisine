package com.batiCuisine.DAO.Interfaces;

import com.batiCuisine.Models.ComponentModel;

import java.awt.*;
import java.sql.SQLException;

public interface ComponentDAO {
    void insertComponent (ComponentModel component) throws SQLException;
}
