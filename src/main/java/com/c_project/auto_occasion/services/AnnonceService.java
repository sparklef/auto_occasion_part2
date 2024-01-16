package com.c_project.auto_occasion.services;

import java.sql.*;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;

@Service
public class AnnonceService {

    public AnnonceService() {
    }
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

   public void createAnnonce(Connection con, int iduser, int idcar, Date date_annonce, int statut, String location, String image_car, String descri) throws Exception {
       PreparedStatement pstmt = null;
       try {
           String query = "INSERT INTO annonce(iduser,idcar,date_annonce,statut,lieu,image_car,description_annonce) VALUES(?,?,?,?,?,?,?)";
           pstmt = con.prepareStatement(query);
           pstmt.setInt(1, iduser);
           pstmt.setInt(2, idcar);
           pstmt.setDate(3, date_annonce);
           pstmt.setInt(4, statut);
           pstmt.setString(5, location);
           pstmt.setString(6, image_car);
           pstmt.setString(7, descri);
           System.out.println("Saving an annonce in the table annonce successful");
           System.out.println(query);
           pstmt.executeUpdate();

       } catch (SQLException e) {
           System.out.println("Error while saving the annonce in annonce");
           throw e;
       } finally {
           if(pstmt != null) {
               pstmt.close();
           }
       }
   }

    public void createAnnonce( int iduser, int idcar, Date date_annonce, int statut, String location, String image_car, String descri) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            createAnnonce(con, iduser, idcar, date_annonce, statut, location, image_car, descri);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with insertion in annonce");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

    public void deleteAnnonce(Connection con, int id_annonce) throws Exception {
        Statement stmt = null;
        try{
            String query = "DELETE FROM annonce WHERE idannonce="+ id_annonce +"";
            stmt = con.createStatement();
            System.out.println("Deleting idannonce :"+ id_annonce + " in the table annonce");
            System.out.println(query);

            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while deleting the annonce with id: "+ id_annonce + " in the table annonce");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAnnonce(int id_annonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            deleteAnnonce(con, id_annonce);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while deleting annonce with id: "+id_annonce+" in annonce");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

    public void updateStateAnnonce(Connection con, int state, int id_annonce) throws Exception {
        Statement stmt = null;
        try{
            String query = "UPDATE annonce SET statut="+ state +" WHERE idannonce="+ id_annonce +"";
            stmt = con.createStatement();
            System.out.println("Updating state of annonce with idannonce :"+ id_annonce + " in the table annonce to "+ state +" successful");
            System.out.println(query);
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while updating "+ id_annonce + " in annonce to "+state);
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void updateStateAnnonce(int state, int id_annonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            updateStateAnnonce(con, state, id_annonce);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while updating "+id_annonce+" to state: "+state+" in annonce");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

}
