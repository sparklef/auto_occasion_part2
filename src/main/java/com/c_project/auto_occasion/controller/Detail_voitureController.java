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

import com.c_project.auto_occasion.model.Detail;
import com.c_project.auto_occasion.model.Detail_voiture;
import com.c_project.auto_occasion.services.Detail_voitureService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/detail")
public class Detail_voitureController {
    @Autowired
    private Detail_voitureService detail_voitureService;
    // recherche avancée
      @GetMapping("/search/{keyword}")
      public ResponseEntity<List<Detail>> search(@PathVariable String keyword) {
          try {
              List<Detail> detail_voitures = detail_voitureService.search(keyword);
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
    @GetMapping("/all_detail")
    public ResponseEntity<List<Detail_voiture>> allDetails() {
        try {
            List<Detail_voiture> details = detail_voitureService.allDetails();
            if (details != null) {
                return new ResponseEntity<>(details, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // one detail
    @GetMapping("/one_detail/{id_detail}")
    public ResponseEntity<Detail_voiture> oneDetail(@PathVariable int id_detail) {
        try {
            Detail_voiture detailVoiture = detail_voitureService.getOneDetail(id_detail);
            if (detailVoiture != null) {
                return new ResponseEntity<>(detailVoiture, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
