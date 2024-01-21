package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.StatutDAO;
import com.c_project.auto_occasion.model.Marque;
import com.c_project.auto_occasion.model.Statut;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatutService {
    private StatutDAO statutDAO;
    private Connexion con;

    public StatutService() {
        statutDAO = new StatutDAO();
        con = new Connexion();
    }
    public void create(Statut new_statut) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            statutDAO.createStatut(new_statut);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
    public void update(String statut, int id_statut) throws Exception {
        Connection connection = null;
        try {
            Statut existingStatut = statutDAO.findOne(id_statut);
            if (existingStatut != null) {
                existingStatut.setStatut(statut);
                statutDAO.updateState(existingStatut, id_statut);
            } else {
                System.out.println("No state found with id: " + id_statut);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public void delete(int id_statut) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            statutDAO.delete(id_statut);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public List<Statut> findAllState() throws Exception {
        Connection connection = null;
        List<Statut> statuts = new ArrayList<>();
        try {
            connection = con.getConnection();
            statuts = statutDAO.findAll(connection);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return statuts;
    }
    public Statut findOneState(int id_statut) throws Exception {
        Connection connection = null;
        Statut one_statut = new Statut();
        try {
            connection = con.getConnection();
            one_statut = statutDAO.findOne(id_statut);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return one_statut;
    }
}
