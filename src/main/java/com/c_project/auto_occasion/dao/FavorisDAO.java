package com.c_project.auto_occasion.dao;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Annonce;
import com.c_project.auto_occasion.model.Favoris;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavorisDAO {
    public FavorisDAO() {
    }
    //liste favoris
    public List<Annonce> getFavorisForUser(Connection conn, int idUser) throws SQLException {
        List<Annonce> results = new ArrayList<>();
        Statement stmt=null;
        //  Annonce a=new Annonce();
        try {
            // Créer la requête
            String sql = "SELECT * FROM utilisateur_site us JOIN favoris f ON us.idUser = f.user_id JOIN annonce a ON f.idAnnonce = a.idAnnonce JOIN voiture v ON a.idCar = v.idCar JOIN detail_voiture dv ON v.idDetail = dv.idDetail  JOIN categorie c ON v.idCategorie = c.idCategorie JOIN marque m ON v.idMarque = m.idMarque   WHERE us.idUser = " + idUser;
            // Exécuter la requête
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // Afficher les résultats
            while (rs.next()) {
                int idfavoris=rs.getInt("idfavoris");
                int idannonce=rs.getInt("idAnnonce");
                int iduser = rs.getInt("idUser");
                int idCar = rs.getInt("idCar");
                int status=rs.getInt("statut");
                Timestamp date_annonce = rs.getTimestamp("date_annonce");
                String lieu=rs.getString("lieu");
                String img_car=rs.getString("image_car");
                double prix_vam = rs.getDouble("prix");
                String description=rs.getString("description_annonce");
                boolean validation=rs.getBoolean("validation_annonce");
                results.add(new Annonce(idannonce,iduser, idCar, status,date_annonce,lieu,img_car,prix_vam, description,validation) );
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
    public List<Annonce> getFavorisForUser(int idUser) throws Exception {
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
    // crud
    public void create(Connection con, int id_annonce, int id_user) throws Exception {
        Statement stmt = null;
        try{
            String query = "INSERT INTO favoris(idannonce,user_id) VALUES("+ id_annonce +","+id_user+")";
            stmt = con.createStatement();
            System.out.println("Saving "+ id_annonce +" in the table favoris for the user "+id_user);
            System.out.println(query);
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while saving "+ id_annonce + " in favoris");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void create(int id_annonce, int id_user) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            create(con, id_annonce, id_user);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while inserting "+id_annonce+" in favoris");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    public void update(Connection con, int id_annonce, int id_favoris) throws Exception {
        Statement stmt = null;
        try{
            String query = "UPDATE favoris SET idannonce="+ id_annonce +" WHERE idfavoris="+id_favoris+"";
            stmt = con.createStatement();
            System.out.println("Updating id :"+ id_favoris + " in the table favoris to "+id_annonce+" (idannonce)");
            System.out.println(query);
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while updating idannonce of "+ id_favoris + " in favoris to "+id_annonce);
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void update(int id_annonce, int id_favoris) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            update(con, id_annonce, id_favoris);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while updating idannonce to "+ id_annonce + " in the table favoris for "+id_favoris);
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    public void delete(Connection con,int id_favoris) throws Exception {
        Statement stmt = null;
        try{
            String query = "DELETE FROM favoris WHERE idfavoris="+ id_favoris +"";
            stmt = con.createStatement();
            System.out.println("Deleting id :"+ id_favoris + " in the table favoris");
            System.out.println(query);

            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while deleting "+ id_favoris + " in the table favoris");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void delete(int id_favoris) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            delete(con, id_favoris);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while deleting"+id_favoris+" in favoris");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    // crud
    // get all favorites
    public List<Favoris> findAll(Connection con) throws Exception {
        Statement stmt = null;
        List<Favoris> favoris = new ArrayList<>();
        try{
            String query = "SELECT * FROM favoris";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de tous les favoris...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    int id = rs.getInt("idfavoris");
                    int id_annonce = rs.getInt("idannonce");
                    int id_user = rs.getInt("user_id");
                    favoris.add( new Favoris( id, id_annonce, id_user ) );
                }
                return favoris;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the favorites...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public List<Favoris> findAll() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Favoris> favorite = new ArrayList<>();
        try{
            con = c.getConnection();
            favorite = findAll(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return favorite;
    }
    // get one favorite
    public Favoris findOne(Connection con, int id_favoris) throws Exception {
        Statement stmt = null;
        Favoris one_favorite = new Favoris();
        try {
            String query = "SELECT * FROM favoris WHERE idfavoris="+id_favoris;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Afficher un favoris avec idfavoris="+id_favoris);
            if(!rs.isBeforeFirst()) {
                return null;
            } else {
                while(rs.next()) {
                    int id = rs.getInt("idfavoris");
                    int id_annonce = rs.getInt("idannonce");
                    int id_user = rs.getInt("user_id");
                    one_favorite = new Favoris( id, id_annonce, id_user );
                }
                return one_favorite;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting one favoris...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public Favoris findOne(int id_favoris) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Favoris one_favorite = new Favoris();
        try{
            con = c.getConnection();
            one_favorite = findOne(con, id_favoris);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return one_favorite;
    }
}
