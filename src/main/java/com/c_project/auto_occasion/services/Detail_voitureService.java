package com.c_project.auto_occasion.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.*;

import com.c_project.auto_occasion.dao.Detail_voitureDAO;
import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Detail_voiture;

@Service
public class Detail_voitureService {
    private Detail_voitureDAO detailVoitureDAO;
    private Connexion con;
    public Detail_voitureService() {
        detailVoitureDAO = new Detail_voitureDAO();
        con = new Connexion();
    }
    public void createDetail(Detail_voiture newDetail) throws Exception {
        try {
            detailVoitureDAO.createDetail(newDetail);
        } catch (SQLException e) {
            throw e;
        }
    }
    public void deleteDetail(int id_detail) throws Exception {
        try {
            detailVoitureDAO.deleteDetail(id_detail);
        } catch (SQLException e) {
            throw e;
        }
    }
    public void updateDetail(Detail_voiture update, int id_detail) throws Exception {
        try {
            detailVoitureDAO.updateDetail(update, id_detail);
        } catch (SQLException e) {
            throw e;
        }
    }
    public Detail_voiture getOneDetail(int id_detail) throws Exception {
        Detail_voiture one_detail = new Detail_voiture();
        try {
            one_detail = detailVoitureDAO.oneDetail(id_detail);
        } catch (SQLException e) {
            throw e;
        }
        return one_detail;
    }
}
