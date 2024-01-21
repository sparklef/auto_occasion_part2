package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.AnnonceDAO;
import com.c_project.auto_occasion.model.Annonce;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnnonceService {
    private AnnonceDAO annonceDAO;
    private Connexion con;

    public AnnonceService() {
        annonceDAO = new AnnonceDAO();
        con = new Connexion();
    }
    public void updateStatut(int newState, int id_annonce) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            annonceDAO.updateStatut(newState, id_annonce);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
    public List<Annonce> search(String keyword, Date date, Integer categoryId, BigDecimal price, Integer brandId, String model) throws Exception {
        Connection connection = null;
        List<Annonce> annonces = new ArrayList<>();
        try {
            connection = con.getConnection();
            annonces = annonceDAO.search( keyword, date, categoryId, price, brandId, model );
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return annonces;
    }
}
