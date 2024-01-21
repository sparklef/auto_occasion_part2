package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.Admin_siteDAO;
import com.c_project.auto_occasion.model.Admin_site;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Admin_siteService {
    private Admin_siteDAO adminSiteDAO;
    private Connexion con;
    public Admin_siteService() {
        adminSiteDAO = new Admin_siteDAO();
        con = new Connexion();
    }
    public void create(Admin_site newAdmin) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            adminSiteDAO.create(connection, newAdmin);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
    public void delete(int id_admin) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            adminSiteDAO.delete(id_admin);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public List<Admin_site> findAll() throws Exception {
        Connection connection = null;
        List<Admin_site> admin = new ArrayList<>();
        try {
            connection = con.getConnection();
            admin = adminSiteDAO.findAllAdmin();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return admin;
    }
    public Admin_site findOne(int id_admin) throws Exception {
        Connection connection = null;
        Admin_site one_admin = new Admin_site();
        try {
            connection = con.getConnection();
            one_admin = adminSiteDAO.findById(id_admin);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return one_admin;
    }
    public boolean verif_Admin(String email, String password) throws Exception {
        Connection connection = null;
        boolean return_value = false;
        try {
            connection = con.getConnection();
            return_value = adminSiteDAO.verificationAdmin(email, password);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return return_value;
    }
}
