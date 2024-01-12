package com.c_project.auto_occasion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c_project.auto_occasion.services.AnnonceService;

@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;

      // Update status 
      @PutMapping("/update/{id}")
      public ResponseEntity<String> updateStatut(@PathVariable int id) {
          try {
             annonceService.updateStatut(id);
             return new ResponseEntity<>("Marque updated successfully", HttpStatus.OK);
          } catch (Exception e) {
              e.printStackTrace();
              return new ResponseEntity<>("Error updating marque", HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }
}
