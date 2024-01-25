package com.c_project.auto_occasion.dao;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.*;
import com.c_project.auto_occasion.services.AnnonceService;
import com.c_project.auto_occasion.services.CategorieService;
import com.c_project.auto_occasion.services.Detail_voitureService;
import com.c_project.auto_occasion.services.MarqueService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Detail_voitureDAO {
    /// CRUD
    public void createDetail(Connection con, Detail_voiture detailVoiture) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO detail_voiture (couleur, nbr_portes, boite_devitesse, source_energie, annee , modele) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, detailVoiture.getCouleur());
            pstmt.setInt(2, detailVoiture.getNbr_portes());
            pstmt.setString(3, detailVoiture.getBoite_devitesse());
            pstmt.setString(4, detailVoiture.getSource_energie());
            pstmt.setInt(5, detailVoiture.getAnnee());
            pstmt.setString(6, detailVoiture.getModele());

            System.out.println("Saving the detail_voiture of the car " + detailVoiture.getIdDetail() + " in the table detail_voiture");
            System.out.println(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while saving " + detailVoiture.getIdDetail() + " in detail_voiture");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void createDetail(Detail_voiture detailVoiture) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try {
            con = c.getConnection();
            con.setAutoCommit(false);
            createDetail(con, detailVoiture);
            con.commit();
        } catch (SQLException e) {
            System.out.println("Error persist with insertion in detail_voiture");
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void deleteDetail(Connection con, int id_detail) throws Exception {
        Statement stmt = null;
        try {
            String query = "DELETE FROM detail_voiture WHERE iddetail=" + id_detail + "";
            stmt = con.createStatement();
            System.out.println("Deleting id :" + id_detail + " in the table detail_voiture");
            System.out.println(query);

            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error while deleting " + id_detail + " in the table detail_voiture");
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteDetail(int id_detail) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try {
            con = c.getConnection();
            con.setAutoCommit(false);
            deleteDetail(con, id_detail);
            con.commit();
        } catch (SQLException e) {
            System.out.println("Error persist with deleting the detail_voiture :" + id_detail);
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void updateDetail(Connection con, Detail_voiture detailVoiture, int idDetail) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "UPDATE detail_voiture SET couleur = ?, nbr_portes = ?, boite_devitesse = ?, source_energie = ?, annee = ?, modele = ? WHERE iddetail = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, detailVoiture.getCouleur());
            pstmt.setInt(2, detailVoiture.getNbr_portes());
            pstmt.setString(3, detailVoiture.getBoite_devitesse());
            pstmt.setString(4, detailVoiture.getSource_energie());
            pstmt.setInt(5, detailVoiture.getAnnee());
            pstmt.setString(6, detailVoiture.getModele());
            pstmt.setInt(7, idDetail);  // Use the provided idDetail parameter
            System.out.println("Updating the detail_voiture of the car " + idDetail + " in the table detail_voiture");
            System.out.println(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating " + idDetail + " in detail_voiture");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void updateDetail(Detail_voiture detailVoiture, int idDetail) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try {
            con = c.getConnection();
            con.setAutoCommit(false);
            updateDetail(con, detailVoiture, idDetail);
            con.commit();
        } catch (SQLException e) {
            System.out.println("Error persist with updating the detail_voiture :" + idDetail);
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /// CRUD
    /// get all detail_voiture
    public List<Detail_voiture> allDetail(Connection con) throws Exception {
        Statement stmt = null;
        List<Detail_voiture> details = new ArrayList<>();
        AnnonceService annonceService = new AnnonceService();
        try{
            String query = "SELECT * FROM detail_voiture";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de tous les details des voitures...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    int iddetail = rs.getInt("iddetail");
                    String couleur = rs.getString("couleur");
                    int nbrPortes = rs.getInt("nbr_portes");
                    String b_v = rs.getString("boite_devitesse");
                    String s_c = rs.getString("source_energie");
                    int year = rs.getInt("annee");
                    String modele = rs.getString("modele");

                    details.add(new Detail_voiture(iddetail, couleur, nbrPortes, b_v, s_c, year, modele));
                }
                return details;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the cars details...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public List<Detail_voiture> allDetail() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Detail_voiture> detailsVoitures = new ArrayList<>();
        try {
            con = c.getConnection();
            detailsVoitures = allDetail(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return detailsVoitures;
    }
    /// get one detail_voiture
    public Detail_voiture oneDetail(Connection con, int id_detail) throws Exception {
        Statement stmt = null;
        Detail_voiture one_d_voiture = new Detail_voiture();
        AnnonceService a_service = new AnnonceService();
        try {
            String query = "SELECT * FROM detail_voiture WHERE iddetail="+id_detail;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Afficher un detail avec iddetail="+id_detail);
            if(!rs.isBeforeFirst()) {
                return null;
            } else {
                while(rs.next()) {
                    int iddetail = rs.getInt("iddetail");
                    String couleur = rs.getString("couleur");
                    int nbrPortes = rs.getInt("nbr_portes");
                    String b_v = rs.getString("boite_devitesse");
                    String s_c = rs.getString("source_energie");
                    int year = rs.getInt("annee");
                    String modele = rs.getString("modele");

                    one_d_voiture= new Detail_voiture(iddetail, couleur, nbrPortes, b_v, s_c, year, modele);
                }
                return one_d_voiture;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting one details about one car...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public Detail_voiture oneDetail(int id_detail) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Detail_voiture one_detail = new Detail_voiture();
        try{
            con = c.getConnection();
            one_detail = oneDetail(con, id_detail);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return one_detail;
    }
}

