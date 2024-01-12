package com.c_project.auto_occasion.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Categorie;

@Service
public class CategorieService {

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

    public void insertionCategorie(String nomCategorie) throws Exception{
        Connexion co=new Connexion();
        Connection c=null;
        Statement stat=null;
        try {
            c=co.getConnection();
            stat=c.createStatement();
            String query=String.format("INSERT INTO categorie VALUES(DEFAULT,'%s')", nomCategorie);
            int row=stat.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }finally{
            if (stat != null) stat.close();
            if (c != null) c.close();
        }
    }
    public void deleteCategorie(int id) throws Exception{
        Connexion co=new Connexion();
        Connection c=null;
        Statement stat=null;
        try {
            c=co.getConnection();
            stat=c.createStatement();
            String query=String.format("DELETE FROM categorie WHERE idCategorie=%d", id);
            int row=stat.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }finally{
            if (stat != null) stat.close();
            if (c != null) c.close();
        }
    }
    public void updateCategorie(int id,String nom) throws Exception{
        Connexion co=new Connexion();
        Connection c=null;
        Statement stat=null;
        try {
            c=co.getConnection();
            stat=c.createStatement();
            String query=String.format("UPDATE categorie SET categorie='%s' WHERE idCategorie =%d",nom ,id);
            int row=stat.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }finally{
            if (stat != null) stat.close();
            if (c != null) c.close();
        }
    }
}
