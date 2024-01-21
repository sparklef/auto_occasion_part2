package com.c_project.auto_occasion.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.c_project.auto_occasion.model.Annonce;
import com.c_project.auto_occasion.services.AnnonceService;

@RestController
@RequestMapping("/api/annonce")
@CrossOrigin(origins = "*")
public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;

      // Update status 
      @PutMapping("/update/{id}")
      public ResponseEntity<String> updateStatut(@RequestBody int newState ,@PathVariable int id) {
          try {
             annonceService.updateStatut(newState, id);
             return new ResponseEntity<>("Annonce's state updated successfully", HttpStatus.OK);
          } catch (Exception e) {
              e.printStackTrace();
              return new ResponseEntity<>("Error updating Annonce's state", HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }

      //recherche avance
      @GetMapping("/search/{keyword}/{date}/{categoryid}/{price}/{brandeId}/{model}")
      public ResponseEntity<List<Annonce>> search(@PathVariable String keyword,@PathVariable Date date,@PathVariable Integer categoryId,@PathVariable BigDecimal price,@PathVariable Integer brandId,@PathVariable String model ) {
          try {
              List<Annonce> annonces = annonceService.search(keyword, date, categoryId, price, brandId, model);
              if (annonces != null) {
                  return new ResponseEntity<>(annonces, HttpStatus.OK);
              } else {
                  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
              }
          } catch (Exception e) {
              e.printStackTrace();
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }


  


      
}
