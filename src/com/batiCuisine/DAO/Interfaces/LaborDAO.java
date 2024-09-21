package com.batiCuisine.DAO.Interfaces;

import com.batiCuisine.Models.ComponentModel;
import com.batiCuisine.Models.LaborModel;
import com.batiCuisine.Models.MaterialModel;

import java.sql.SQLException;
import java.util.List;

public interface LaborDAO {
    void insertLabor(LaborModel labor , ComponentModel component) throws SQLException;
    List<LaborModel> getAllLaborForProject(int projectId) throws SQLException;
}
