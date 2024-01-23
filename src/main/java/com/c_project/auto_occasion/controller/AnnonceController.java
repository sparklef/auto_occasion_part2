package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Annonce;
import com.c_project.auto_occasion.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

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

   
    @PostMapping("/create_annonce")
    public ResponseEntity<String> createAnnonce(@RequestBody Annonce newAnnonce) {
        try {
            annonceService.create(newAnnonce);
            return new ResponseEntity<>("Annonce created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating annonce", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete_annonce/{id_annonce}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable int id_annonce) {
        try {
            annonceService.delete(id_annonce);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all_annonce")
    public ResponseEntity<List<Annonce>> allAnnonce() {
        try {
            List<Annonce> annonces = annonceService.allAnnonces();
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
    @GetMapping("/one_annonce/{id_annonce}")
    public ResponseEntity<Annonce> oneAnnonce(@PathVariable int id_annonce) {
        try {
            Annonce annonce = annonceService.oneAnnonce(id_annonce);
            if (annonce != null) {
                return new ResponseEntity<>(annonce, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/annonces_of_user/{id_user}")
    public ResponseEntity<List<Annonce>> allAnnoncesOfAnUser(@PathVariable int id_user) {
        try {
            List<Annonce> annonces = annonceService.findAllUser_s_Annonces(id_user);
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
    @GetMapping("one_annonce/{id_user}/{id_annonce}")
    public ResponseEntity<Annonce> oneUserAnnonce(@PathVariable int id_user, @PathVariable int id_annonce) {
        try {
            Annonce annonce = annonceService.findOneAnnonceOfAnUser(id_user, id_annonce);
            if (annonce != null) {
                return new ResponseEntity<>(annonce, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
