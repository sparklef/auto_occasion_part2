package com.c_project.auto_occasion.dao;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Stat;
import com.c_project.auto_occasion.model.Statistique;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class StatistiqueDAO {

    public Statistique Findstatistique(Connection con) throws Exception {
        Statement stmt = null;
        Statistique statistique = new Statistique();
        try {
            String query = "SELECT "+
          "(SELECT COUNT(*) FROM utilisateur_site) AS totalUtilisateur,"+
           "(SELECT COUNT(*) FROM annonce WHERE validation_annonce=true) AS nombreAnnonceConfirmer,"+
           "(SELECT COUNT(*) FROM voiture) AS totalVoiture,"+ 
           "(SELECT SUM(prix * 0.2) FROM annonce) AS chiffreAffaire";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Afficher un statistique");
            if(!rs.isBeforeFirst()) {
                return null;
            } else {
                while(rs.next()) {
                    int nombreutilisateur =rs.getInt("totalutilisateur");
                    int nombreAnnonceConfirmer=rs.getInt("nombreannonceconfirmer");
                    int totalVoiture=rs.getInt("totalvoiture");
                    double chiffreAffaire=rs.getDouble("chiffreAffaire");

                    statistique= new Statistique(nombreutilisateur,nombreAnnonceConfirmer,totalVoiture,chiffreAffaire);
                }
                return statistique;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting statistique...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public Statistique Findstatistique() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Statistique statistique = new Statistique();
        try{
            con = c.getConnection();
            statistique = Findstatistique(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return statistique;
    }

    public List<Stat> FindStat(Connection con) throws Exception {
        Statement stmt = null;
        List<Stat> statistiques = new ArrayList<>();
        try{
            String query = "select c.categorie, count(c.categorie) as nbcategorie, m.marque, count(m.marque) as nbMarque, d.couleur, count(d.couleur) as nbCouleur "+
                    "from voiture v "+
                    "join categorie c on v.idCategorie=c.idCategorie "+
                    "join marque m on v.idMarque=m.idMarque "+
                    "join detail_voiture d on v.idDetail=d.idDetail "+
                    "group by c.categorie, m.marque, d.modele, d.couleur";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de tous les statistiques ...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    String categorie=rs.getString("categorie");
                    int nbcategorie=rs.getInt("nbcategorie");
                    String marque=rs.getString("marque");
                    int nbmarque=rs.getInt("nbmarque");
                    String couleur=rs.getString("couleur");
                    int nbcouleur=rs.getInt("nbcouleur");

                    statistiques.add(new Stat(categorie,nbcategorie,marque,nbmarque,couleur,nbcouleur));
                }
                return statistiques;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the statistiques...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public List<Stat> FindStat() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Stat> statistiques = new ArrayList<>();
        try{
            con = c.getConnection();
            statistiques = FindStat(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return statistiques;
    }

}