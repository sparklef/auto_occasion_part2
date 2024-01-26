package com.c_project.auto_occasion.services;

import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.VoitureDAO;
import com.c_project.auto_occasion.model.Voiture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class VoitureService {
    private Connexion connex;
    private VoitureDAO voitureDAO;

    public VoitureService() {
        voitureDAO = new VoitureDAO();
        connex = new Connexion();
    }
    public List<Voiture> findall() throws Exception {
        Connection connection = null;
        List<Voiture> all_voitures = new ArrayList<>();
        try {
            connection = connex.getConnection();
            all_voitures = voitureDAO.findAll();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return all_voitures;
    }
    public Voiture findone(int id_car) throws Exception {
        Connection connection = null;
        Voiture one_voiture = new Voiture();
        try {
            connection = connex.getConnection();
            one_voiture = voitureDAO.findOne(id_car);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return one_voiture;
    }
    //new voiture
    public void create(Voiture voiture) throws Exception {
        Connection connection = null;
        try {
            connection = connex.getConnection();
            voitureDAO.create(connection, voiture);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }

    public void updateVoiture(Voiture voiture,int id_car) throws Exception {
        Connection connection = null;
        try {
            connection = connex.getConnection();
            voitureDAO.update(voiture,id_car);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
    public void delete(int idcar) throws Exception {
        Connection connection = null;
        try {
            connection = connex.getConnection();
            voitureDAO.delete(idcar);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
}
