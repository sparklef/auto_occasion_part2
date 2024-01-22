package com.c_project.auto_occasion.dao;

import com.c_project.auto_occasion.model.Detail_voiture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Detail_voitureDAO {
    /// CRUD
    public void createDetail(Connection con, Detail_voiture detailVoiture) throws Exception {
        PreparedStatement pstmt = null;
        try {

        } catch (SQLException e) {
            System.out.println("Error while saving " + detailVoiture.getIdUser() + " in annonce");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    /// CRUD
}
