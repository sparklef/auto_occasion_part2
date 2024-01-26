package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.FavorisDAO;
import com.c_project.auto_occasion.model.Annonce;
import com.c_project.auto_occasion.model.Favoris;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavorisService {
    private FavorisDAO favorisDAO;
    private Connexion con;

    public FavorisService() {
        favorisDAO = new FavorisDAO();
        con = new Connexion();
    }
    // all user's fav by his id
    public List<Annonce> allUserSFavoris(int id_user) throws Exception {
        Connection connection = null;
        List<Annonce> annoncesFav = new ArrayList<>();
        try {
            connection = con.getConnection();
            annoncesFav = favorisDAO.getFavorisForUser(id_user);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return annoncesFav;
    }
    public void createFav(int id_annonce, int id_user) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            favorisDAO.create(id_annonce, id_user);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
    public void updateFav(int id_annonce, int id_favoris) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            favorisDAO.update(id_annonce, id_favoris);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
    public void deleteFav(int id_favoris) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            favorisDAO.delete(id_favoris);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
    public List<Favoris> allFav() throws Exception {
        Connection connection = null;
        List<Favoris> all_favoris_stored = new ArrayList<>();
        try {
            connection = con.getConnection();
            all_favoris_stored = favorisDAO.findAll();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return all_favoris_stored;
    }
   
}
