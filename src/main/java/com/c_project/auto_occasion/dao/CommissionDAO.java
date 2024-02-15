package com.c_project.auto_occasion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Commission;

public class CommissionDAO {

    public CommissionDAO() {
    }

   
    public double calculateChiffreAffaire(Connection con) throws SQLException {
        double chiffreAffaire =  0.0;
        double commission =  0.0;
    
        // Récupérer la dernière commission de la base de données
        String sqlGetLastCommission = "SELECT commission_pourcent FROM commission ORDER BY idcommission DESC LIMIT  1";
        try (PreparedStatement pstmt = con.prepareStatement(sqlGetLastCommission)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    commission = rs.getDouble("commission_pourcent");
                }
            }
        }
    
        // Si aucune commission n'a été trouvée, utiliser une valeur par défaut ou lever une exception
        if (commission ==  0.0) {
            // Vous pouvez choisir de lever une exception ou d'utiliser une valeur par défaut
            throw new SQLException("No commission  found in the database.");
        }
    
        // Utiliser la commission récupérée pour calculer le chiffre d'affaires
        String sqlCalculateChiffreAffaire = "SELECT SUM(prix * ?) AS chiffreAffaire FROM annonce";
        try (PreparedStatement pstmt = con.prepareStatement(sqlCalculateChiffreAffaire)) {
            pstmt.setDouble(1, commission /  100.0); // Convertir le pourcentage en décimal
    
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    chiffreAffaire = rs.getDouble("chiffreAffaire");
                }
            }
        }
    
        return chiffreAffaire;
    }
     
    public double createPercentage(Connection con, Commission commission) throws Exception {
        double chiffreAffaire =   0.0;
    
        try {
            // Log the commission value before attempting to save it
            System.out.println("Attempting to save commission value: " +  commission.getCommission_pourcent());
    
            // Insérer la nouvelle commission dans la base de données
            try (PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO commission(commission_pourcent) VALUES (?)")) {
    
                pstmt.setDouble(1,  commission.getCommission_pourcent() );  // Utiliser le montant de commission calculé
    
                System.out.println("Saving " + commission.getCommission_pourcent()  + " in the table commission");
    
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows >  0) {
                    System.out.println("Successfully saved.");
                } else {
                    System.out.println("No rows were affected during the save operation.");
                }
            } catch (SQLException e) {
                System.out.println("Error while saving " + commission.getCommission_pourcent() + " in commission");
                throw e;
            }
    
            // Appeler la fonction calculateChiffreAffaire pour calculer le chiffre d'affaires
            chiffreAffaire = calculateChiffreAffaire(con);
            System.out.println("Chiffre d'affaires calculé : " + chiffreAffaire);
    
        } catch (SQLException e) {
            System.out.println("Error while getting last commission percentage");
            throw e;
        }
    
        // Retourner la valeur du chiffre d'affaires
        return chiffreAffaire;
    }
    
    
    
    public double create(Commission voiture) throws Exception {
        double chiffreAffaire =  0.0;
        Connexion c = new Connexion();
        Connection con = null;
        try {
            con = c.getConnection();
            con.setAutoCommit(false);
            // Appeler la méthode createPercentage qui retourne maintenant un double
            chiffreAffaire = createPercentage(con, voiture);
            con.commit();
        } catch (SQLException e) {
            System.out.println("Error persist with insertion in commission");
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        // Retourner la valeur du chiffre d'affaires
        return chiffreAffaire;
    }
    
    
}
