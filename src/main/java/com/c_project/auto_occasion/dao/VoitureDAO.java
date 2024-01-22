package com.c_project.auto_occasion.dao;

import java.util.ArrayList;
import java.util.List;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Categorie;
import com.c_project.auto_occasion.model.Detail_voiture;
import com.c_project.auto_occasion.model.Marque;
import com.c_project.auto_occasion.model.Voiture;

import java.sql.*;
public class VoitureDAO {

    public VoitureDAO() {
    }
       // crud
    // get all voiture
    public List<Voiture> findAll(Connection con) throws Exception {
        Statement stmt = null;
        List<Voiture> voitures = new ArrayList<>();
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
                    String matricule = rs.getString("matricule");
                    double prix = rs.getDouble("prix");
                    int idmarque = rs.getInt("idmarque");
                    int idcategorie = rs.getInt("idcategorie");
                    int iddetail = rs.getInt("iddetail");
    
                    // Assuming you have methods to fetch Marque, Categorie, and Detail_voiture objects using their IDs
                    Marque marque_voiture =new Marque();
                    marque_voiture.setIdMarque(idmarque);
                    
                    Categorie categorie = new Categorie();
                    categorie.setIdCategorie(idcategorie);
                    Detail_voiture detail =new Detail_voiture();
                   detail.setIdDetail(iddetail);
                   voitures.add(new Voiture(idcar, matricule, prix, marque_voiture, categorie, detail));
                }
                return voitures;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the favorites...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    
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
    // get one favorite
    public Voiture findOne(Connection con, int idcar) throws Exception {
        Statement stmt = null;
        Voiture one_voiture = new Voiture();
        try {
            String query = "SELECT * FROM Voiture WHERE idcar="+idcar;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Afficher un Voiture avec idfavoris="+idcar);
            if(!rs.isBeforeFirst()) {
                return null;
            } else {
                while(rs.next()) {
                    String matricule = rs.getString("matricule");
                    double prix = rs.getDouble("prix");
                    int idmarque = rs.getInt("idmarque");
                    int idcategorie = rs.getInt("idcategorie");
                    int iddetail = rs.getInt("iddetail");
    
                    // Assuming you have methods to fetch Marque, Categorie, and Detail_voiture objects using their IDs
                    Marque marque_voiture =new Marque();
                    marque_voiture.setIdMarque(idmarque);
                    
                    Categorie categorie = new Categorie();
                    categorie.setIdCategorie(idcategorie);
                    Detail_voiture detail =new Detail_voiture();
                   detail.setIdDetail(iddetail);
                   one_voiture= new Voiture(idcar, matricule, prix, marque_voiture, categorie, detail);
                }
                return one_voiture;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting one favoris...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

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

    
}
