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
      private Detail_voitureDAO detail_voiture_DAO;
      private DetailDAO detailDAO;
      private Connexion con;
      public Detail_voitureService()
      {
          detail_voiture_DAO = new Detail_voitureDAO();
          detailDAO = new DetailDAO();
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
    public List<Detail_voiture> allDetails() throws Exception {
        List<Detail_voiture> all_details = new ArrayList<>();
        try {
            all_details = detail_voiture_DAO.allDetail();
        } catch (SQLException e) {
            throw e;
        }
        return all_details;
    }

    public Detail_voiture getOneDetail(int id_detail) throws Exception {
        Detail_voiture one_detail = new Detail_voiture();
        try {
            one_detail = detail_voiture_DAO.oneDetail(id_detail);
        } catch (SQLException e) {
            throw e;
        }
        return one_detail;
    }
}
