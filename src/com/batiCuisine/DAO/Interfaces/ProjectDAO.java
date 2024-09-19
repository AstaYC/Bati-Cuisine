package com.batiCuisine.DAO.Interfaces;

import com.batiCuisine.Models.ProjectModel;

import java.sql.SQLException;

public interface ProjectDAO {
    void insertProject(ProjectModel project) throws SQLException;

}

