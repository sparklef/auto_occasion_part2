package com.c_project.auto_occasion.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.*;

import com.c_project.auto_occasion.dao.Detail_voitureDAO;
import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.DetailDAO;
import com.c_project.auto_occasion.model.Detail_voiture;

@Service
public class Detail_voitureService {
      private DetailDAO detailDAO;
      private Connexion con;
      public Detail_voitureService()
      {
           detailDAO=new DetailDAO();
           con=new Connexion();

      }
      public List<Detail_voiture> search(String keyword) throws Exception {
        Connection connection = null;
        List<Detail_voiture> detail_voitures = new ArrayList<>();
        try {
            connection = con.getConnection();
            detail_voitures = detailDAO.search(keyword);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return detail_voitures;
    }
    
}
