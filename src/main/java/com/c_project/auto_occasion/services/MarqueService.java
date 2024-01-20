package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.MarqueDAO;
import com.c_project.auto_occasion.model.Marque;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarqueService {
    private MarqueDAO marqueDAO;
    private Connexion con;
    public MarqueService() {
        marqueDAO = new MarqueDAO();
        con = new Connexion();
    }
    // create a new marque
    public void create(Marque marque) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            marqueDAO.create(connection, marque);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
    // get all marques
    public List<Marque> findAll() throws Exception {
        Connection connection = null;
        List<Marque> marques = new ArrayList<>();
        try {
            connection = con.getConnection();
            marques = marqueDAO.findAll(connection);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return marques;
    }
    // get one marque
    public Marque findOne(int id_marque) throws Exception {
        Connection connection = null;
        Marque one_marque = new Marque();
        try {
            connection = con.getConnection();
            one_marque = marqueDAO.findOne(connection, id_marque);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return one_marque;
    }

    public void delete(int id) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            marqueDAO.delete(id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void update(String marqueName, int id) throws Exception {
        Connection connection = null;
        try {
            Marque existingMarque = marqueDAO.findOne(id);
            if (existingMarque != null) {
                existingMarque.setMarque(marqueName);
                marqueDAO.update(existingMarque, id);
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
}
