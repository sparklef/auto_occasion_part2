package com.c_project.auto_occasion.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Annonce;

@Service
public class AnnonceService {

    public AnnonceService() {
    }
    //update status=1 in annonce
    public void updateStatut(Connection con,int idAnnonce) throws SQLException {
        PreparedStatement stmt=null;
        try {
            String sql = "UPDATE annonce SET statut = 1 WHERE idAnnonce = ?";
             stmt = con.prepareStatement(sql);
            stmt.setInt(1, idAnnonce);
            
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
    public void updateStatut(int idAnnonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
           updateStatut(con, idAnnonce);
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
    public List<Annonce> advancedSearch(Connection connection,String keyword, Date date, Integer categoryId, BigDecimal price, Integer brandId, String model) throws SQLException {
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
            Annonce annonce = new Annonce(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getDate(4),resultSet.getInt(5),
            resultSet.getString(6),resultSet.getString(7),resultSet.getString(8));
            
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





    public  List<Annonce> getFavorisForUser(Connection conn,int idUser) throws SQLException {
        List<Annonce> results = new ArrayList<>();
        Statement stmt=null;
    //  Annonce a=new Annonce();
        try {
         
            
            // Créer la requête
            String sql = "SELECT * FROM utilisateur_site us JOIN annonce a ON us.idUser = a.idUser JOIN voiture v ON a.idCar = v.idCar JOIN favoris f ON a.idAnnonce = f.idAnnonce JOIN detail_voiture dv ON v.idDetail = dv.idDetail JOIN categorie c ON v.idCategorie = c.idCategorie JOIN marque m ON v.idMarque = m.idMarque WHERE us.idUser=" + idUser;
            
            // Exécuter la requête
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            // Afficher les résultats
            while (rs.next()) {
                int idannonce=rs.getInt("idAnnonce");
                int iduser = rs.getInt("idUser");
              int idCar = rs.getInt("idCar");
                Date date_annonce = rs.getDate("date_annonce");
                int status=rs.getInt("statut");
                String lieu=rs.getString("lieu");
                String img_car=rs.getString("image_car");
                String description=rs.getString("description_annonce");
             //   results.add(a);
             results.add(new Annonce(idannonce,iduser, idCar, date_annonce,status,lieu,img_car, description) );

            }
             return results;
         
            } catch (SQLException e) {
        System.out.println("Error with getting all the favorites...");
            throw e;
           } finally {
          
            if(stmt != null) {
            stmt.close();
            }
         }
        
     }


    public  List<Annonce> getFavorisForUser(int idUser) throws Exception {
        List<Annonce> results = new ArrayList<>();
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            results = getFavorisForUser(con, idUser);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        
        return results;
    }

}
