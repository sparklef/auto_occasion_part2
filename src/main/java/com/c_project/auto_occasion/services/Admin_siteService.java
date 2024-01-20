package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Admin_site;
import com.c_project.auto_occasion.model.Categorie;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

@Service
public class Admin_siteService {
    public Admin_siteService() {
    }

    public void create(Connection con, String email, String nom, String prenom, String mdp, String contact) throws Exception {
        Statement stmt = null;
        try{
            String query = "INSERT INTO admin_site(email,nom,prenom,mdp,contact) VALUES('"+ email +"','"+ nom +"','"+ prenom +"','"+ mdp +"','"+ contact +"')";
            stmt = con.createStatement();
            System.out.println("Saving "+ email +", "+ nom +","+ prenom +","+ mdp +","+ contact +" in the table admin_site");
            System.out.println(query);
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while saving "+ email +", "+ nom +","+ prenom +","+ mdp +","+ contact +" in admin_site");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void create(String email, String nom, String prenom, String mdp, String contact) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            create(con, email,nom,prenom,mdp,contact);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with insertion in admin_site");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

    public void delete(Connection con, int id_admin) throws Exception {
        Statement stmt = null;
        try{
            String query = "DELETE FROM admin_site WHERE idadmin = "+id_admin;
            stmt = con.createStatement();
            System.out.println("Deleting the admin identified by idadmin : " + id_admin + " succesfull");
            System.out.println(query);
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while Deleting the admin identified by idadmin : " + id_admin);
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(int id_admin) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            delete(con, id_admin);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with deleting in admin_site");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

    // trouver un admin avec son email
    public Admin_site findByEmail(Connection con, String email) throws Exception {
        Admin_site admin = new Admin_site();
        Statement stmt = null;
        ResultSet res=null;
        try {
            String query = "SELECT * FROM admin_site WHERE email = " + email;
            stmt = con.createStatement();
            res=stmt.executeQuery(query);
            while (res.next()) {
                admin=new Admin_site(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("Error while finding the user with email " + email + " in admin_site");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
            if(res != null) {
                res.close();
            }
        }
        return admin;
    }

    public Admin_site findByEmail(String email) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Admin_site admin = new Admin_site();
        try{
            con = c.getConnection();
            admin = findByEmail(con, email);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return admin;
    }
    /*
    public Connexion login_admin(Connexion co, String email, String mdp) throws Exception {
        boolean arePasswordsEqual = true;
        // Call the findByEmail function
        Admin_site admin = findByEmail(email);
        // If the admin is found, compare the passwords
        if(admin != null) {
            // Here, you need to add a function to hash the input password

            // and compare it with the hashed password stored in the admin object.

            // This function should be something like:

            // boolean arePasswordsEqual = checkPassword(mdp, admin.getMdp());


            if(arePasswordsEqual) {
                System.out.println("Connexion réussie");
                co.setConnected(true);
                co.setAdmin(admin);
            } else {
                System.out.println("Le mot de passe est incorrect");
            }
        } else {
            System.out.println("L'email est incorrect");
        }
        return co;
    }
    */
    public boolean verifyAdmin(Connection con, String email, String password) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM admin_site WHERE email = ? AND mdp = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Admin with the email " + email + " found");
                return count > 0;
            } else {
                System.out.println("Admin with the email : " + email + " not found");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (con != null && rs != null) {
                con.close();
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return false;
    }
}
