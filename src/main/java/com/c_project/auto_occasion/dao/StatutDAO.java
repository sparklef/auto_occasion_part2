package com.c_project.auto_occasion.dao;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Marque;
import com.c_project.auto_occasion.model.Statut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatutDAO {
    public void createStatut(Connection co, Statut newStatut) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO statut(statut) VALUES(?)";
            pstmt = co.prepareStatement(query);
            pstmt.setString(1, newStatut.getStatut());
            System.out.println("Saving " + newStatut.getStatut() + " in the table statut");
            System.out.println(query);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while saving " + newStatut.getStatut() + " in statut");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    public void createStatut(Statut newStatut) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            createStatut(con, newStatut);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with insertion in statut");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    public void updateState(Connection con, Statut statut, int id_statut) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "UPDATE statut SET statut=? WHERE idstatut=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, statut.getStatut());
            pstmt.setInt(2, id_statut);
            System.out.println("Updating id: " + id_statut + " in the table statut to " + statut.getStatut());
            System.out.println(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating " + id_statut + " in statut to " + statut.getStatut());
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    public void updateState(Statut statut, int id_statut) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            updateState(con, statut, id_statut);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while updating "+id_statut+" to "+statut+" in statut");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    public void delete(Connection con,int id_statut) throws Exception {
        Statement stmt = null;
        try{
            String query = "DELETE FROM statut WHERE idstatut="+ id_statut +"";
            stmt = con.createStatement();
            System.out.println("Deleting id :"+ id_statut + " in the table statut");
            System.out.println(query);

            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while deleting "+ id_statut + " in the table statut");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void delete(int id_statut) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            delete(con, id_statut);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while deleting"+id_statut+" in statut");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    // get all statut
    public List<Statut> findAll(Connection con) throws Exception {
        Statement stmt = null;
        List<Statut> statuts = new ArrayList<>();
        try{
            String query = "SELECT * FROM statut";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de toutes les statuts...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    int id = rs.getInt("idstatut");
                    String nom = rs.getString("statut");
                    statuts.add( new Statut( id, nom ) );
                }
                return statuts;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the statuts...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public List<Statut> findAll() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Statut> statut = new ArrayList<>();
        try{
            con = c.getConnection();
            statut = findAll(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return statut;
    }
    // get one statut
    public Statut findOne(Connection con, int id_statut) throws Exception {
        Statement stmt = null;
        Statut one_statut = new Statut();
        try {
            String query = "SELECT * FROM statut WHERE idstatut="+id_statut;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Afficher une statut avec idstatut="+id_statut);
            if(!rs.isBeforeFirst()) {
                return null;
            } else {
                while(rs.next()) {
                    int id = rs.getInt("idstatut");
                    String nom = rs.getString("statut");
                    one_statut = new Statut( id, nom );
                }
                return one_statut;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting one statut...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public Statut findOne(int id_statut) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Statut one_statut = new Statut();
        try{
            con = c.getConnection();
            one_statut = findOne(con, id_statut);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return one_statut;
    }
}
