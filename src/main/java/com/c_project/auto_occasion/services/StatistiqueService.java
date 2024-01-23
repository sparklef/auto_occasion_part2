package com.c_project.auto_occasion.services;

import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.StatistiqueDAO;
import com.c_project.auto_occasion.model.Stat;
import com.c_project.auto_occasion.model.Statistique;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class StatistiqueService {
    private StatistiqueDAO statistiqueDAO;
    private Connexion con; 

    public StatistiqueService() {
        statistiqueDAO = new StatistiqueDAO();
        con = new Connexion();
    }

    public Statistique FindStatistique() throws Exception {
        Connection connection = null;
        Statistique stat = new Statistique();
        try {
            connection = con.getConnection();
            stat = statistiqueDAO.Findstatistique();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return stat;
    }

    public List<Stat> FindStat() throws Exception {
        Connection connection = null;
        List<Stat> statistiques = new ArrayList<>();
        try {
            connection = con.getConnection();
            statistiques = statistiqueDAO.FindStat();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return statistiques;
    }
}
