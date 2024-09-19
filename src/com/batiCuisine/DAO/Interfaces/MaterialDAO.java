package com.batiCuisine.DAO.Interfaces;

import com.batiCuisine.Models.MaterialModel;

import java.sql.SQLException;

public interface MaterialDAO {
    void insertMaterial(MaterialModel material) throws SQLException;
}
