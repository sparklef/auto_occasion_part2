package com.c_project.auto_occasion.services;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.CommissionDAO;
import com.c_project.auto_occasion.model.Commission;

@Service
public class CommissionService {
    private Connexion connex;
    private CommissionDAO commissionDao;
    public CommissionService() {
        this.connex =new Connexion();
        this.commissionDao = new CommissionDAO();
    }

    public void create(Commission voiture) throws Exception {
        Connection connection = null;
        try {
            connection = connex.getConnection();
            commissionDao.create(voiture);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }

}
