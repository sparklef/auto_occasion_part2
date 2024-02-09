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

    public double create(Commission voiture) throws Exception {
        double chiffreAffaire =   0.0;
        try {
            Connection connection = connex.getConnection();
            // Appeler la méthode create de commissionDao qui retourne maintenant un double
            chiffreAffaire = commissionDao.create(voiture);
        } catch (SQLException e) {
            throw e;
        }
        // Retourner la valeur du chiffre d'affaires
        return chiffreAffaire;
    }
        
   
    
}
