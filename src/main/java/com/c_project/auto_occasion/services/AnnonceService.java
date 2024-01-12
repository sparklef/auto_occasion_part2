package com.c_project.auto_occasion.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;

@Service
public class AnnonceService {

    //update status=1 in annonce
    public void updateStatut(Connection con,int idAnnonce) throws SQLException {
        PreparedStatement stmt=null;
        try {
            String sql = "UPDATE annonce SET statut = 1 WHERE idAnnonce = ?";
             stmt = con.prepareStatement(sql);
            stmt.setInt(1, idAnnonce);
            
            stmt.executeUpdate();
          System.out.println("Updating status with success");
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la mise à jour du statut.", ex);
        }
         finally {
        if(stmt != null) {
            stmt.close();
        }
       }
    }
    public void updateStatut(int idAnnonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
           updateStatut(con, idAnnonce);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while updating"+idAnnonce+" in annonce");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

    
}
