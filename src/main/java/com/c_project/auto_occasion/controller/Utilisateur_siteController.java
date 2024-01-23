package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Utilisateur_site;
import com.c_project.auto_occasion.services.Utilisateur_siteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class Utilisateur_siteController {
    @Autowired
    private Utilisateur_siteService utilisateur_siteService;

      // Create
      @PostMapping("/create_user")
      public ResponseEntity<String> createUser(@RequestBody Utilisateur_site newUser) {
          try {
              utilisateur_siteService.create(newUser);
              return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
          } catch (Exception e) {
              e.printStackTrace();
              return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }

    @PostMapping("/verif")
    public ResponseEntity<String> verifUser(@RequestBody Utilisateur_site user) throws Exception {
        Connexion con = new Connexion();
        Connection co = con.getConnection();
        String token = null;
        try {
            token = utilisateur_siteService.verificationUser(co, user.getEmail(), user.getMdp());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error verification user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
