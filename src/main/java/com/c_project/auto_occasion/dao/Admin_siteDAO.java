package com.c_project.auto_occasion.dao;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Admin_site;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Admin_siteDAO {
    public Admin_siteDAO() {
    }

    /// CRUD
    public void create(Connection con, Admin_site newAdmin) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO admin_site(email, nom, prenom, mdp, contact) VALUES(?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newAdmin.getEmail());
            pstmt.setString(2, newAdmin.getNom());
            pstmt.setString(3, newAdmin.getPrenom());
            pstmt.setString(4, newAdmin.getMdp());
            pstmt.setString(5, newAdmin.getContact());
            System.out.println("Saving " + newAdmin.getEmail() + " in the table admin_site");
            System.out.println(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while saving " + newAdmin.getEmail() + " in admin_site");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    public void create(Admin_site newAdmin) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            create(con, newAdmin);
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
    public void update(Connection con, Admin_site update_adminSite, int id_admin) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "UPDATE admin_site SET email = ?, nom = ?, prenom = ?, mdp = ?, contact = ? WHERE idadmin = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, update_adminSite.getEmail());
            pstmt.setString(2, update_adminSite.getNom());
            pstmt.setString(3, update_adminSite.getPrenom());
            pstmt.setString(4, update_adminSite.getMdp());
            pstmt.setString(5, update_adminSite.getContact());
            pstmt.setInt(6, id_admin);
            System.out.println("Updating the admin " + id_admin + " in the table admin_site");
            System.out.println(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating " + id_admin + " in admin_site");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    public void update(Admin_site update_adminSite, int id_admin) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            update(con, update_adminSite, id_admin);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while updating"+id_admin+" in admin_site");
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
            String query = "DELETE FROM admin_site WHERE idadmin="+ id_admin +"";
            stmt = con.createStatement();
            System.out.println("Deleting id :"+ id_admin + " in the table admin_site");
            System.out.println(query);

            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while deleting "+ id_admin + " in the table admin_site");
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
            System.out.println("Error while deleting"+id_admin+" in admin_site");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
    /// CRUD
    public boolean verificationAdmin(Connection con, String email, String password) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        boolean verif_admin = false;
        try {
            String sql = "SELECT * FROM admin_site WHERE email = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(sql);
            if (!rs.next()) {
                System.out.println("Vide");
                return verif_admin;
            } else {
                String storedPassword = rs.getString("mdp");
                if (storedPassword != null && storedPassword.equals(password)) {
                    System.out.println("Email and password are correct");
                    verif_admin = true;
                } else {
                    System.out.println("Wrong password");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while verifying the admin");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return verif_admin;
    }
    public boolean verificationAdmin(String email, String password) throws SQLException, Exception {
        Connexion c = new Connexion();
        Connection con = null;
        boolean verif_admin = false;
        try{
            con = c.getConnection();
            verif_admin = verificationAdmin(con, email, password);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return verif_admin;
    }
    /// trouver un admin par son identifiant
    public Admin_site findById(Connection con, int id_admin) throws Exception {
        Admin_site admin = new Admin_site();
        Statement stmt = null;
        ResultSet res=null;
        try {
            String query = "SELECT * FROM admin_site WHERE idadmin = " + id_admin;
            stmt = con.createStatement();
            res=stmt.executeQuery(query);
            while (res.next()) {
                admin=new Admin_site(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("Error while finding the user with the id " + id_admin + " in admin_site");
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
    public Admin_site findById(int id_admin) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Admin_site admin = new Admin_site();
        try{
            con = c.getConnection();
            admin = findById(con, id_admin);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return admin;
    }
    /// Trouver tous les admins
    public List<Admin_site> findAllAdmin(Connection con) throws Exception {
        Statement stmt = null;
        List<Admin_site> admin = new ArrayList<>();
        try {
            String query = "SELECT * FROM admin_site";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de touts les admin...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    int id = rs.getInt("idadmin");
                    String mail = rs.getString("email");
                    String name = rs.getString("nom");
                    String surname = rs.getString("prenom");
                    String password = rs.getString("mdp");
                    String contact = rs.getString("contact");
                    admin.add( new Admin_site( id, mail, name, surname, password, contact ) );
                }
                return admin;
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
    public List<Admin_site> findAllAdmin() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Admin_site> admin = new ArrayList<>();
        try{
            con = c.getConnection();
            admin = findAllAdmin(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return admin;
    }
    /// Validation d'une annonce
    public void validerAnnonce(Connection con, int id_annonce) throws Exception {
        PreparedStatement stmt=null;
        try {
            String sql = "UPDATE annonce SET validation_annonce = true WHERE idAnnonce = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_annonce);
            stmt.executeUpdate();
            System.out.println("Updating validation_annonce for the annonce :"+id_annonce);
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la mise à jour de la validation_annonce.", ex);
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void validerAnnonce(int id_annonce) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            validerAnnonce(con, id_annonce);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }
}
