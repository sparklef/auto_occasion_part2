package com.c_project.auto_occasion.dao;

import java.util.ArrayList;
import java.util.List;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Categorie;
import com.c_project.auto_occasion.model.Detail_voiture;
import com.c_project.auto_occasion.model.Marque;
import com.c_project.auto_occasion.model.Voiture;
import com.c_project.auto_occasion.services.CategorieService;
import com.c_project.auto_occasion.services.Detail_voitureService;
import com.c_project.auto_occasion.services.MarqueService;

import java.sql.*;
public class VoitureDAO {

    public VoitureDAO() {
    }
    // crud
    // get all voiture
    public List<Voiture> findAll(Connection con) throws Exception {
        Statement stmt = null;
        List<Voiture> voitures = new ArrayList<>();
        MarqueService m_service = new MarqueService();
        CategorieService c_service = new CategorieService();
        Detail_voitureService dv_service = new Detail_voitureService();
        try{
            String query = "SELECT * FROM voiture";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de tous les voitures...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    int idcar = rs.getInt("idcar");
                    String nom_vam = rs.getString("nom_voiture");
                    String matricule = rs.getString("matricule");
                    int idmarque = rs.getInt("idmarque");
                    int idcategorie = rs.getInt("idcategorie");
                    int iddetail = rs.getInt("iddetail");
    
                    // Assuming you have methods to fetch Marque, Categorie, and Detail_voiture objects using their IDs
                    Marque marque = m_service.findOne(idmarque);
                    Categorie categorie = c_service.findOne(idcategorie);
                    Detail_voiture detail_car = dv_service.getOneDetail(iddetail);
                   voitures.add(new Voiture(idcar, nom_vam,matricule, marque, categorie, detail_car));
                }
                return voitures;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the cars...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    // get all voiture
    public List<Voiture> findAll() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Voiture> voitures = new ArrayList<>();
        try{
            con = c.getConnection();
            voitures = findAll(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return voitures;
    }
    // get one car
    public Voiture findOne(Connection con, int idcar) throws Exception {
        Statement stmt = null;
        Voiture one_voiture = new Voiture();
        MarqueService m_service = new MarqueService();
        CategorieService c_service = new CategorieService();
        Detail_voitureService dv_service = new Detail_voitureService();
        try {
            String query = "SELECT * FROM voiture WHERE idcar="+idcar;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Afficher un Voiture avec idcar="+idcar);
            if(!rs.isBeforeFirst()) {
                return null;
            } else {
                while(rs.next()) {
                    int id_car = rs.getInt("idcar");
                    String nom_vam = rs.getString("nom_voiture");
                    String matricule = rs.getString("matricule");
                    int idmarque = rs.getInt("idmarque");
                    int idcategorie = rs.getInt("idcategorie");
                    int iddetail = rs.getInt("iddetail");
    
                    // Assuming you have methods to fetch Marque, Categorie, and Detail_voiture objects using their IDs
                    Marque marque = m_service.findOne(idmarque);
                    Categorie categorie = c_service.findOne(idcategorie);
                    Detail_voiture detail_car = dv_service.getOneDetail(iddetail);

                   one_voiture= new Voiture(id_car,nom_vam, matricule, marque, categorie, detail_car);
                }
                return one_voiture;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting one car...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    // get one car
    public Voiture findOne(int id_car) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Voiture one_voiture = new Voiture();
        try{
            con = c.getConnection();
            one_voiture = findOne(con, id_car);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return one_voiture;
    }
    // creation d'une voiture
    public void create(Connection con,String nom_vam, String matricule,int idmarque,int idcategorie,int iddetail) throws Exception {
        Statement stmt = null;
        try{
            String query = "INSERT INTO voiture(nom_voiture,matricule,idmarque,idcategorie,iddetail) VALUES('"+nom_vam+"'','"+matricule+"',"+idmarque+","+idcategorie+","+iddetail+")";
            stmt = con.createStatement();
            System.out.println("Saving  in the table voiture");
            System.out.println(query);
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while saving  in voiture");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    // creation d'une voiture
    public void create(String nom_vam, String matricule,int idmarque,int idcategorie,int iddetail) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            create(con, nom_vam, matricule, idmarque,idcategorie, iddetail);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while inserting  in voiture");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    // suppression d'une voiture
    public void delete(Connection con,int id_car) throws Exception {
        Statement stmt = null;
        try{
            String query = "DELETE FROM voiture WHERE idcar="+ id_car +"";
            stmt = con.createStatement();
            System.out.println("Deleting id :"+ id_car + " in the table voiture");
            System.out.println(query);

            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while deleting "+ id_car + " in the table voiture");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    // suppression d'une voiture
    public void delete(int idcar) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            delete(con, idcar);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while deleting"+idcar+" in voiture");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    
}
