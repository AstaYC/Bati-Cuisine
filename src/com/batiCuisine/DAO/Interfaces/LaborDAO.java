package com.batiCuisine.DAO.Interfaces;

import com.batiCuisine.Models.ComponentModel;
import com.batiCuisine.Models.LaborModel;
import com.batiCuisine.Models.MaterialModel;

import java.sql.SQLException;

public interface LaborDAO {
    void insertLabor(LaborModel labor , ComponentModel component) throws SQLException;
}
