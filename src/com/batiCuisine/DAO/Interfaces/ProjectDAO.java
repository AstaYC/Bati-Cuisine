package com.batiCuisine.DAO.Interfaces;

import com.batiCuisine.Models.ProjectModel;

import java.sql.SQLException;

public interface ProjectDAO {
    int insertProject(ProjectModel project) throws SQLException;
    ProjectModel getLastProject () throws SQLException;
    void setCostMarginProject(int id , double profitmerge , double totalcost)throws SQLException;

}

