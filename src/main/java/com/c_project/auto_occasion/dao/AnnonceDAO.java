package com.c_project.auto_occasion.dao;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Admin_site;
import com.c_project.auto_occasion.model.Annonce;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnonceDAO {
    public AnnonceDAO() {
    }
    /// Toutes les annonces
    public List<Annonce> findAllAnnonce(Connection con) throws Exception {
        Statement stmt = null;
        List<Annonce> annonces = new ArrayList<>();
        try {
            String query = "SELECT * FROM annonce";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de toutes les annonces...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    int id_annonce = rs.getInt("idannonce");
                    int id_user = rs.getInt("iduser");
                    int id_car = rs.getInt("idcar");
                    int statut = rs.getInt("statut");
                    Timestamp date_annonce = rs.getTimestamp("date_annonce");
                    String toerana = rs.getString("lieu");
                    String image = rs.getString("image_car");
                    String descri_annonce = rs.getString("description_annonce");
                    boolean etat_validation_annonce = rs.getBoolean("validation_annonce");
                    String nom_voiture = rs.getString("nom_voiture");
                    annonces.add( new Annonce( id_annonce, id_user, id_car, statut, date_annonce, toerana, image, descri_annonce, etat_validation_annonce, nom_voiture ) );
                }
                return annonces;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the admin...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    /// Toutes les annonces
    public List<Annonce> findAllAnnonce() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Annonce> annonces = new ArrayList<>();
        try{
            con = c.getConnection();
            annonces = findAllAnnonce(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return annonces;
    }
    /// Une annonce en particulier
    public Annonce findOneAnnonce(Connection con, int id_annonce) throws Exception {
        Annonce annonce = new Annonce();
        Statement stmt = null;
        ResultSet res=null;
        try {
            String query = "SELECT * FROM annonce WHERE idannonce= " + id_annonce;
            stmt = con.createStatement();
            res=stmt.executeQuery(query);
            while (res.next()) {
                annonce=new Annonce(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),res.getTimestamp(5),res.getString(6),res.getString(7),res.getString(8),res.getBoolean(9),res.getString(10));
            }
        } catch (SQLException e) {
            System.out.println("Error while finding the user with the id " + id_annonce + " in annonce");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
            if(res != null) {
                res.close();
            }
        }
        return annonce;
    }
    /// une annonce en particulier
    public Annonce findOneAnnonce(int id_annonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Annonce annonce = new Annonce();
        try{
            con = c.getConnection();
            annonce = findOneAnnonce(con, id_annonce);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return annonce;
    }
    /// creation d'une nouvelle annonce
    public void createAnnonce(Connection con, Annonce newAnnonce) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO annonce(iduser, idcar, statut, date_annonce, lieu, image_car, description_annonce, validation_annonce, nom_voiture) VALUES(?, ?, 1, CURRENT_TIMESTAMP, ?, ?, ?, false, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, newAnnonce.getIdUser());
            pstmt.setInt(2, newAnnonce.getIdCar());
            pstmt.setString(3, newAnnonce.getLieu());
            pstmt.setString(4, newAnnonce.getImage_car());
            pstmt.setString(5, newAnnonce.getDescription());
            pstmt.setString(6, newAnnonce.getNom_voiture());

            System.out.println("Saving the annonce of the user " + newAnnonce.getIdUser() + " in the table annonce");
            System.out.println(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while saving " + newAnnonce.getIdUser() + " in annonce");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    /// creation d'une nouvelle annonce
    public void createAnnonce(Annonce newAnnonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            createAnnonce(con, newAnnonce);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with insertion in annonce");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    /// supprimer une annonce
    public void deleteAnnonce(Connection con, int id_annonce) throws Exception {
        Statement stmt = null;
        try{
            String query = "DELETE FROM annonce WHERE idannonce="+ id_annonce +"";
            stmt = con.createStatement();
            System.out.println("Deleting id :"+ id_annonce + " in the table annonce");
            System.out.println(query);

            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while deleting "+ id_annonce + " in the table annonce");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void deleteAnnonce(int id_annonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            deleteAnnonce(con, id_annonce);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with deleting the annonce :"+id_annonce);
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
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
    
    /*public List<Annonce> advancedSearch(Connection connection, String keyword, Date date, Integer categoryId, BigDecimal price, Integer brandId, String model) throws SQLException {
        List<Annonce> results = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM annonce JOIN voiture ON annonce.idCar = voiture.idCar JOIN marque ON voiture.idMarque = marque.idMarque WHERE ");
        boolean firstCondition = true;
        if (keyword != null) {
            sql.append("(voiture.matricule LIKE '%").append(keyword).append("%' OR marque.marque LIKE '%").append(keyword).append("%')");
            firstCondition = false;
        }
        if (date != null) {
            if (!firstCondition) sql.append(" OR ");
            sql.append("annonce.date_annonce = '").append(date).append("'");
            firstCondition = false;
        }
        if (categoryId != null) {
            if (!firstCondition) sql.append(" OR ");
            sql.append("voiture.idCategorie = ").append(categoryId);
            firstCondition = false;
        }
        if (price != null) {
            if (!firstCondition) sql.append(" OR ");
            sql.append("voiture.prix <= ").append(price);
            firstCondition = false;
        }
        if (brandId != null) {
            if (!firstCondition) sql.append(" OR ");
            sql.append("voiture.idMarque = ").append(brandId);
            firstCondition = false;
        }
        if (model != null) {
            if (!firstCondition) sql.append(" OR ");
            sql.append("detail_voiture.modele LIKE '%").append(model).append("%'");
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql.toString());
        while (resultSet.next()) {
            Annonce annonce = new Annonce(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getBoolean(9));
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
    }*/
    /// maka annonces nataon'ny utilisateur ray
    public List<Annonce> findAllUserSAnnonce(Connection con, int id_user) throws SQLException {
        List<Annonce> annonces = new ArrayList<>();
        String query = "SELECT * FROM annonce WHERE idUser = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id_user);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Annonce annonce = new Annonce();
                    annonce.setIdAnnonce(rs.getInt("idAnnonce"));
                    annonce.setIdUser(rs.getInt("idUser"));
                    annonce.setIdCar(rs.getInt("idCar"));
                    annonce.setStatut(rs.getInt("statut"));
                    annonce.setDate_annonce(rs.getTimestamp("date_annonce"));
                    annonce.setLieu(rs.getString("lieu"));
                    annonce.setImage_car(rs.getString("image_car"));
                    annonce.setDescription(rs.getString("description_annonce"));
                    annonce.setValidation_annonce(rs.getBoolean("validation_annonce"));
                    annonce.setNom_voiture(rs.getString("nom_voiture"));
                    annonces.add(annonce);
                }
            }
        }
        return annonces;
    }


    /// maka annonces nataon'ny utilisateur ray
    public List<Annonce> findAllUserSAnnonce(int id_user) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Annonce> user_s_annonces = new ArrayList<>();
        try {
            con = c.getConnection();
            user_s_annonces = findAllUserSAnnonce(con ,id_user);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return user_s_annonces;
    }
    /// maka annonce ray an'ny utilisateur ray

    public Annonce findOneAnnonceOfAnUser(Connection con, int id_user, int id_annonce) throws Exception {
        PreparedStatement stmt = null;
        Annonce one_annonceOfanUser = null;
        try {
            String query = "SELECT * FROM annonce WHERE iduser=? AND idannonce=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id_user);
            stmt.setInt(2, id_annonce);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Afficher l'annonce de l'utilisateur="+id_user+" avec l'idannonce="+id_annonce);
            if(rs.next()) {
                int id = rs.getInt("idannonce");
                int iduser = rs.getInt("iduser");
                int id_car = rs.getInt("idcar");
                int statut = rs.getInt("statut");
                Timestamp date_annonce = rs.getTimestamp("date_annonce");
                String lieu = rs.getString("lieu");
                String imagevoiture = rs.getString("image_car");
                String description = rs.getString("description_annonce");
                boolean validation = rs.getBoolean("validation_annonce");
                String nom_voiture = rs.getString("nom_voiture");
                one_annonceOfanUser = new Annonce(id, iduser, id_car, statut, date_annonce, lieu, imagevoiture, description, validation, nom_voiture) ;
            }
        } catch (SQLException e) {
            System.out.println("Error while getting the annonce " + id_annonce + " of the user : "+id_user);
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
        return one_annonceOfanUser;
    }
    /// maka annonce ray an'ny utilisateur ray
    public Annonce findOneAnnonceOfAnUser(int id_user, int id_annonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Annonce oneAnnonceOfAnUser = new Annonce();
        try {
            con = c.getConnection();
            oneAnnonceOfAnUser = findOneAnnonceOfAnUser(con, id_user, id_annonce);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return oneAnnonceOfAnUser;
    }
    // annonces non validées
    public List<Annonce> findAllAnnonceNonValidee(Connection con) throws Exception {
        List<Annonce> annonces_nonvalidees = new ArrayList<>();
        String query = "SELECT * FROM annonce WHERE validation_annonce = false";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Annonce annonce = new Annonce();
                    annonce.setIdAnnonce(rs.getInt("idAnnonce"));
                    annonce.setIdUser(rs.getInt("idUser"));
                    annonce.setIdCar(rs.getInt("idCar"));
                    annonce.setStatut(rs.getInt("statut"));
                    annonce.setDate_annonce(rs.getTimestamp("date_annonce"));
                    annonce.setLieu(rs.getString("lieu"));
                    annonce.setImage_car(rs.getString("image_car"));
                    annonce.setDescription(rs.getString("description_annonce"));
                    annonce.setValidation_annonce(rs.getBoolean("validation_annonce"));
                    annonce.setNom_voiture(rs.getString("nom_voiture"));
                    annonces_nonvalidees.add(annonce);
                }
            }
        }
        return annonces_nonvalidees;
    }
    public List<Annonce> findAllAnnonceNonValidee() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Annonce> annonces_nonvalidees = new ArrayList<>();
        try {
            con = c.getConnection();
            annonces_nonvalidees = findAllAnnonceNonValidee(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return annonces_nonvalidees;
    }
    // Annonce validée
    public List<Annonce> findAllAnnonceValidees(Connection con) throws Exception {
        List<Annonce> annonces_validees = new ArrayList<>();
        String query = "SELECT * FROM annonce WHERE validation_annonce = true";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Annonce annonce = new Annonce();
                    annonce.setIdAnnonce(rs.getInt("idAnnonce"));
                    annonce.setIdUser(rs.getInt("idUser"));
                    annonce.setIdCar(rs.getInt("idCar"));
                    annonce.setStatut(rs.getInt("statut"));
                    annonce.setDate_annonce(rs.getTimestamp("date_annonce"));
                    annonce.setLieu(rs.getString("lieu"));
                    annonce.setImage_car(rs.getString("image_car"));
                    annonce.setDescription(rs.getString("description_annonce"));
                    annonce.setValidation_annonce(rs.getBoolean("validation_annonce"));
                    annonce.setNom_voiture(rs.getString("nom_voiture"));
                    annonces_validees.add(annonce);
                }
            }
        }
        return annonces_validees;
    }
    public List<Annonce> findAllAnnonceValidees() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Annonce> annonces_validees = new ArrayList<>();
        try {
            con = c.getConnection();
            annonces_validees = findAllAnnonceValidees(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return annonces_validees;
    }
}
