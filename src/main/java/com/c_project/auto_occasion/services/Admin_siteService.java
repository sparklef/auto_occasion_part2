package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.Admin_siteDAO;
import com.c_project.auto_occasion.model.Admin_site;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

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
    public void delete() throws Exception {

    }
}
