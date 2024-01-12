package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Marque;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarqueService {
    // crud
    public void create(Connection con, String marque) throws Exception {
        Statement stmt = null;
        try{
            String query = "INSERT INTO marque(marque) VALUES('"+ marque +"')";
            stmt = con.createStatement();
            System.out.println("Saving "+ marque +" in the table marque");
            System.out.println(query);
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while saving "+ marque + " in marque");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void create(String marque) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            create(con, marque);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with insertion in marque");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    public void update(Connection con, String marque, int id_marque) throws Exception {
        Statement stmt = null;
        try{
            String query = "UPDATE marque SET marque='"+ marque +"' WHERE idmarque="+ id_marque +"";
            stmt = con.createStatement();
            System.out.println("Updating id :"+ id_marque + " in the table marque to "+marque);
            System.out.println(query);
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while updating "+ id_marque + " in marque to "+marque);
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void update(String marque, int id_marque) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            update(con, marque, id_marque);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while updating "+id_marque+" to "+marque+" in marque");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    public void delete(Connection con,int id_marque) throws Exception {
        Statement stmt = null;
        try{
            String query = "DELETE FROM marque WHERE idmarque="+ id_marque +"";
            stmt = con.createStatement();
            System.out.println("Deleting id :"+ id_marque + " in the table marque");
            System.out.println(query);

            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while deleting "+ id_marque + " in the table marque");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void delete(int id_marque) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            delete(con, id_marque);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while deleting"+id_marque+" in marque");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    // crud
    // get all marque
    public List<Marque> findAll(Connection con) throws Exception {
        Statement stmt = null;
        List<Marque> marque = new ArrayList<>();
        try{
            String query = "SELECT * FROM marque";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de toutes les marques...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    int id = rs.getInt("idmarque");
                    String nom = rs.getString("marque");
                    marque.add( new Marque( id, nom ) );
                }
                return marque;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the marque...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    // get all marque
    public List<Marque> findAll() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Marque> marque = new ArrayList<>();
        try{
            con = c.getConnection();
            marque = findAll(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return marque;
    }
    // get one marque
    public Marque findOne(Connection con, int id_marque) throws Exception {
        Statement stmt = null;
        Marque one_marque = new Marque();
        try {
            String query = "SELECT * FROM marque WHERE idmarque="+id_marque;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Afficher une marque avec idmarque="+id_marque);
            if(!rs.isBeforeFirst()) {
                return null;
            } else {
                while(rs.next()) {
                    int id = rs.getInt("idmarque");
                    String nom = rs.getString("marque");
                    one_marque = new Marque( id, nom );
                }
                return one_marque;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting one marque...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    // get one marque
    public Marque findOne(int id_marque) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Marque one_marque = new Marque();
        try{
            con = c.getConnection();
            one_marque = findOne(con, id_marque);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return one_marque;
    }
}
