package com.c_project.auto_occasion.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Detail;
public class DetailDAO {

    public DetailDAO() {
    }
    public List<Detail> findAllDetail(Connection con) throws Exception {
        Statement stmt = null;
        List<Detail> annonces = new ArrayList<>();
        try {
            String query = "select a.date_annonce,a.lieu,a.image_car,a.description_annonce,c.categorie,v.prix,v.matricule,m.marque,d.modele,d.couleur,d.nbr_portes,d.boite_devitesse,d.source_energie,d.annee"+
            "from annonce a"+ 
           "join voiture v"+
            "on a.idCar=v.idCar"+
            "join categorie c"+ 
           "on v.idCategorie=c.idCategorie"+
            "join marque m"+
            "on v.idMarque=m.idMarque"+
            "join detail_voiture d"+
            "on v.idDetail=d.idDetail";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de toutes les details...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    String couleur = rs.getString("couleur");
                    int nbr_portes = rs.getInt("nbr_portes");
                    String boite_vitesse = rs.getString("boite_vitesse");
                    String source_energie = rs.getString("source_energie");
                    Date date_annonce = rs.getDate("date_annonce");
                    String toerana = rs.getString("lieu");
                    String image = rs.getString("image_car");
                    String descri_annonce = rs.getString("description_annonce");
                    int annee=rs.getInt("annee");
                    String  modele=rs.getString("modele");
                    String categorie=rs.getString("categorie");
                    double prix=rs.getDouble("prix");
                    String matricule=rs.getString("matricule");
                    String marque=rs.getString("marque");
                    annonces.add( new Detail(couleur,nbr_portes,boite_vitesse,source_energie,annee,modele,date_annonce,toerana,image,descri_annonce,categorie,prix,matricule,marque));
                }
                return annonces;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the detail...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public List<Detail> rechercheAvancer(Connection con, String keyword) throws Exception {
        Statement stmt = null;
        List<Detail> annonces = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("select a.date_annonce,a.lieu,a.image_car,a.description_annonce,c.categorie,a.prix,v.matricule,m.marque,d.modele,d.couleur,d.nbr_portes,d.boite_devitesse,d.source_energie,d.annee"+
            " from annonce a "+
            " join voiture v "+
            " on a.idCar=v.idCar "+
            " join categorie c "+
            " on v.idCategorie=c.idCategorie "+
            " join marque m "+
            " on v.idMarque=m.idMarque "+
            " join detail_voiture d "+
            " on v.idDetail=d.idDetail "+
            " WHERE ");
            sql.append("(a.date_annonce::VARCHAR LIKE '%").append(keyword).append("%' OR a.lieu LIKE '%").append(keyword).append("%' OR a.image_car LIKE '%").append(keyword).append("%' OR a.description_annonce LIKE '%").append(keyword).append("%' OR c.categorie LIKE '%").append(keyword).append("%' OR CAST(a.prix AS VARCHAR) LIKE '%").append(keyword).append("%' OR v.matricule LIKE '%").append(keyword).append("%' OR m.marque LIKE '%").append(keyword).append("%' OR d.modele LIKE '%").append(keyword).append("%' OR d.couleur LIKE '%").append(keyword).append("%' OR CAST(d.nbr_portes AS VARCHAR) LIKE '%").append(keyword).append("%' OR d.boite_devitesse LIKE '%").append(keyword).append("%' OR d.source_energie LIKE '%").append(keyword).append("%' OR d.annee::VARCHAR LIKE '%").append(keyword).append("%')");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql.toString());
            System.out.println("Affichage de toutes les details...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    String couleur = rs.getString("couleur");
                    int nbr_portes = rs.getInt("nbr_portes");
                    String boite_vitesse = rs.getString("boite_devitesse");
                    String source_energie = rs.getString("source_energie");
                    Date date_annonce = rs.getDate("date_annonce");
                    String toerana = rs.getString("lieu");
                    String image = rs.getString("image_car");
                    String descri_annonce = rs.getString("description_annonce");
                    int annee=rs.getInt("annee");
                    String modele=rs.getString("modele");
                    String categorie=rs.getString("categorie");
                    double prix=rs.getDouble("prix");
                    String matricule=rs.getString("matricule");
                    String marque=rs.getString("marque");
                    annonces.add( new Detail(couleur,nbr_portes,boite_vitesse,source_energie,annee,modele,date_annonce,toerana,image,descri_annonce,categorie,prix,matricule,marque));
                }
                return annonces;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the detail...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    
    
    

    public List<Detail> search(String keyword) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Detail> sList = new ArrayList<>();
        try{
            con = c.getConnection();
            sList = rechercheAvancer(con, keyword);
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
