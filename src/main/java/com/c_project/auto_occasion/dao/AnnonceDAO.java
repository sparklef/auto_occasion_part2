package com.c_project.auto_occasion.dao;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Annonce;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnonceDAO {
    //update status
    public void updateStatut(Connection con, int newStatut, int idAnnonce) throws SQLException {
        PreparedStatement stmt=null;
        try {
            String sql = "UPDATE annonce SET statut = ? WHERE idAnnonce = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, newStatut);
            stmt.setInt(2, idAnnonce);
            stmt.executeUpdate();
            System.out.println("Updating status with success");
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la mise à jour du statut.", ex);
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void updateStatut(int newStatut, int idAnnonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            updateStatut(con, newStatut, idAnnonce);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while updating"+idAnnonce+" in annonce");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    public List<Annonce> advancedSearch(Connection connection, String keyword, Date date, Integer categoryId, BigDecimal price, Integer brandId, String model) throws SQLException {
        List<Annonce> results = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM annonce JOIN voiture ON annonce.idCar = voiture.idCar JOIN marque ON voiture.idMarque = marque.idMarque WHERE ");
        boolean firstCondition = true;
        if (keyword != null) {
            sql.append("(voiture.matricule LIKE '%").append(keyword).append("%' OR marque.marque LIKE '%").append(keyword).append("%')");
            firstCondition = false;
        }
        if (date != null) {
            if (!firstCondition) sql.append(" AND ");
            sql.append("annonce.date_annonce = '").append(date).append("'");
            firstCondition = false;
        }
        if (categoryId != null) {
            if (!firstCondition) sql.append(" AND ");
            sql.append("voiture.idCategorie = ").append(categoryId);
            firstCondition = false;
        }
        if (price != null) {
            if (!firstCondition) sql.append(" AND ");
            sql.append("voiture.prix <= ").append(price);
            firstCondition = false;
        }
        if (brandId != null) {
            if (!firstCondition) sql.append(" AND ");
            sql.append("voiture.idMarque = ").append(brandId);
            firstCondition = false;
        }
        if (model != null) {
            if (!firstCondition) sql.append(" AND ");
            sql.append("detail_voiture.modele LIKE '%").append(model).append("%'");
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql.toString());
        while (resultSet.next()) {
            Annonce annonce = new Annonce(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDate(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getBoolean(9));
            results.add(annonce);
        }
        return results;
    }
    public List<Annonce> search(String keyword, Date date, Integer categoryId, BigDecimal price, Integer brandId, String model) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Annonce> sList = new ArrayList<>();
        try{
            con = c.getConnection();
            sList = advancedSearch(con, keyword, date, categoryId, price, brandId, model);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return sList;
    }
}
