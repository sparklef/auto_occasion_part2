package com.c_project.auto_occasion.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.c_project.auto_occasion.dao.CategorieDAO;
import com.c_project.auto_occasion.model.Marque;
import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Categorie;

@Service
public class CategorieService {
    private CategorieDAO categorieDAO;
    private Connexion con;
    public CategorieService() {
        categorieDAO = new CategorieDAO();
        con = new Connexion();
    }

    public void create(Categorie nomCategorie) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            categorieDAO.insertionCategorie(nomCategorie);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }

    public void update(int id ,String nomCategorie) throws Exception {
        Connection connection = null;
        try {
            Categorie existingCate = categorieDAO.getCategorieSpecifique(id);
            if (existingCate != null) {
                existingCate.setCategorie(nomCategorie);
                categorieDAO.updateCategorie(id, existingCate);
            } else {
                System.out.println("No marque found with id: " + id);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void delete(int id) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            categorieDAO.deleteCategorie(id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Categorie findOne(int id_categorie) throws Exception {
        Connection connection = null;
        Categorie one_categorie = new Categorie();
        try {
            connection = con.getConnection();
            one_categorie = categorieDAO.getCategorieSpecifique(id_categorie);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return one_categorie;
    }

    public List<Categorie> findAll() throws Exception {
        Connection connection = null;
        List<Categorie> categories = new ArrayList<>();
        try {
            connection = con.getConnection();
            categories = categorieDAO.getAllCategorie();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return categories;
    }

}
