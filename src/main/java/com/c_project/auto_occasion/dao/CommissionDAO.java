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

    public void createPercentage(Connection con, Commission commission) throws Exception {
        try {
    
            try (PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO commission(idAnnonce, commission_percentage, commission_amount, commission_status) VALUES (?, ?, ?, 'payé')")) {
    
                pstmt.setInt(1, commission.getIdAnnonce().getIdAnnonce());
                pstmt.setDouble(2, commission.getCommission_percentage());
                pstmt.setDouble(3,0.0);  // Use the calculated commission amount
    
                System.out.println("Saving " + commission.getIdAnnonce() + " in the table commission");
    
                pstmt.executeUpdate();
                System.out.println("Successfully saved.");
            } catch (SQLException e) {
                System.out.println("Error while saving " + commission.getIdAnnonce() + " in commission");
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Error while getting last commission percentage");
            throw e;
        } 
    }
    

    public void create(Connection con, Commission commission) throws Exception {
        try {
            // Retrieve the last commission percentage from the database
           // int lastAnnonceId = commission.getIdAnnonce().getIdAnnonce();
            double lastCommissionPercentage = getLastCommissionPercentage(con);
            double prixAnnonce = getPrixByIdAnnonce(con, commission);
    
            // Calculate commission amount based on the given price and commission percentage
            double commissionAmount = (lastCommissionPercentage * prixAnnonce) / 100.0;
    
            try (PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO commission(idAnnonce, commission_percentage, commission_amount, commission_status) VALUES (?, ?, ?, 'payé')")) {
    
                pstmt.setInt(1, commission.getIdAnnonce().getIdAnnonce());
                pstmt.setDouble(2, lastCommissionPercentage);
                pstmt.setDouble(3, commissionAmount);  // Use the calculated commission amount
    
                System.out.println("Saving " + lastCommissionPercentage + " in the table commission");
    
                pstmt.executeUpdate();
                System.out.println("Successfully saved.");
            } catch (SQLException e) {
                System.out.println("Error while saving " + lastCommissionPercentage+ " in commission");
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Error while getting last commission percentage");
            throw e;
        }
    }
    
    
    private double getLastCommissionPercentage(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT commission_percentage FROM commission ORDER BY idAnnonce DESC LIMIT 1");
    
            if (rs.next()) {
                return rs.getDouble("commission_percentage");
            } else {
                // Return a default value if no records are found
                return 0.0;
            }
        }
    }
  
    private double getPrixByIdAnnonce(Connection con,Commission commission) throws SQLException {
        int annonce=commission.getIdAnnonce().getIdAnnonce(); 
        try (PreparedStatement pstmt = con.prepareStatement("SELECT prix FROM annonce WHERE idAnnonce = ?")) {
            pstmt.setInt(1, annonce);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                return rs.getDouble("prix");
            } else {
                // Retourner une valeur par défaut si aucun enregistrement n'est trouvé
                return 0.0;
            }
        }
    }
    
    public void create(Commission commission) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try {
            con = c.getConnection();
            con.setAutoCommit(false);
    
            // Vérifier s'il existe des valeurs dans la table commission
            if (hasValuesInCommission(con)) {
                System.out.println("tato izy");
                create(con, commission);
            } else {
                System.out.println(" tsy tato izy");
                createPercentage(con, commission);
                create(con, commission);
            }
    
            con.commit();
        } catch (SQLException e) {
            System.out.println("Error persist with insertion in commission");
            con.rollback(); // En cas d'erreur, annuler la transaction
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    private boolean hasValuesInCommission(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM commission");
    
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
    
            return false;
        }
    }
    
  

}
