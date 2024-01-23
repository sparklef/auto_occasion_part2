package com.c_project.auto_occasion.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.c_project.auto_occasion.connexion.Connexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c_project.auto_occasion.model.Detail_voiture;
import com.c_project.auto_occasion.services.Detail_voitureService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/detail")
public class Detail_voitureController {
    @Autowired
    private Detail_voitureService detail_voitureService;
      @GetMapping("/search/{keyword}")
      public ResponseEntity<List<Detail_voiture>> search(@PathVariable String keyword) {
          try {
              List<Detail_voiture> detail_voitures = detail_voitureService.search(keyword);
              if (detail_voitures != null) {
                  return new ResponseEntity<>(detail_voitures, HttpStatus.OK);
              } else {
                  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
              }
          } catch (Exception e) {
              e.printStackTrace();
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }

    /// CRUD
    /// get all detail_voiture
    public List<Detail_voiture> allDetail(Connection con) throws Exception {
        List<Detail_voiture> details = new ArrayList<>();
        String query = "SELECT * FROM detail_voiture";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Detail_voiture detail = new Detail_voiture();
                    detail.setIdDetail(rs.getInt("iddetail"));
                    detail.setCouleur(rs.getString("couleur"));
                    detail.setNbr_portes(rs.getInt("nbr_portes"));
                    detail.setBoite_devitesse(rs.getString("boite_devitesse"));
                    detail.setSource_energie(rs.getString("source_energie"));
                    detail.setAnnee(rs.getInt("annee"));
                    detail.setModele(rs.getString("modele"));
                    details.add(detail);
                }
            }
        }
        return details;
    }
    public List<Detail_voiture> allDetail() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Detail_voiture> detailsVoitures = new ArrayList<>();
        try {
            con = c.getConnection();
            detailsVoitures = allDetail(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return detailsVoitures;
    }
}
