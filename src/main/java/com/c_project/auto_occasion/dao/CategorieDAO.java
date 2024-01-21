package com.c_project.auto_occasion.dao;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {

    public CategorieDAO() {
    }

    /*CRUD*/
    public List<Categorie> getAllCategorie()throws Exception{
        List<Categorie> liste=new ArrayList<>();
        Connexion co=new Connexion();
        Connection c=null;
        Statement stat=null;
        ResultSet req=null;
        try {
            c=co.getConnection();
            stat=c.createStatement();
            req=stat.executeQuery("SELECT * FROM categorie");
            while (req.next()) {
                Categorie categorie=new Categorie(req.getInt(1),req.getString(2));
                liste.add(categorie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }finally{
            if (req != null) req.close();
            if (stat != null) stat.close();
            if (c != null) c.close();
        }
        return liste;
    }

    public Categorie getCategorieSpecifique(int id) throws Exception{
        Categorie categorie=null;
        Connexion co=new Connexion();
        Connection c=null;
        Statement stat=null;
        ResultSet req=null;
        try {
            c=co.getConnection();
            stat=c.createStatement();
            String query=String.format("SELECT * FROM categorie WHERE idCategorie=%d", id);
            req=stat.executeQuery(query);
            while (req.next()) {
                categorie=new Categorie(req.getInt(1),req.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }finally{
            if (req != null) req.close();
            if (stat != null) stat.close();
            if (c != null) c.close();
        }
        return categorie;
    }
    public void insertionCategorie(Categorie nomCategorie) throws Exception {
        try (Connection c = new Connexion().getConnection();
             PreparedStatement pstmt = c.prepareStatement("INSERT INTO categorie VALUES (DEFAULT, ?)")) {
            pstmt.setString(1, nomCategorie.getCategorie());
            int row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error executing SQL query: " + e.getMessage());
        }
    }
    public void deleteCategorie(int id) throws Exception {
        Connexion co = new Connexion();
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = co.getConnection();
            String query = "DELETE FROM categorie WHERE idCategorie=?";
            pstmt = c.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }
        }
    }
    public void updateCategorie(int id, Categorie nomCategorie) throws Exception {
        Connexion co = new Connexion();
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = co.getConnection();
            String query = "UPDATE categorie SET categorie=? WHERE idCategorie=?";
            pstmt = c.prepareStatement(query);
            pstmt.setString(1, nomCategorie.getCategorie());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }
        }
    }
}
