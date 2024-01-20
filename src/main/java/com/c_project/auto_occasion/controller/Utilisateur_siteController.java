package com.c_project.auto_occasion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c_project.auto_occasion.model.Utilisateur_site;
import com.c_project.auto_occasion.services.Utilisateur_siteService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class Utilisateur_siteController {
    @Autowired
    private Utilisateur_siteService utilisateur_siteService;

      // Create
      @PostMapping("/create/{email}/{nom}/{prenom}/{mdp}/{contact}")
      public ResponseEntity<String> createUser(@PathVariable  String email,@PathVariable String nom,@PathVariable String prenom,@RequestBody String mdp,@PathVariable String contact) {
          try {
            utilisateur_siteService.create(email,nom,prenom,mdp,contact);
              return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
          } catch (Exception e) {
              e.printStackTrace();
              return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }

    /*  
    //verification
    @GetMapping("/verif/{email}/{mdp}")
    public ResponseEntity<String> verifUser(@PathVariable  String email,@PathVariable String mdp) {
        Utilisateur_site user = new Utilisateur_site(email, mdp);
     
        try {
            utilisateur_siteService.verificationUser(email,mdp);           
            return new ResponseEntity<>("User verification successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error verification user", HttpStatus.INTERNAL_SERVER_ERROR);
        }         
    }
    */

    @PostMapping("/verif")
    public ResponseEntity<String> verifUser(@RequestBody Utilisateur_site user) {
        try {
            utilisateur_siteService.verificationUser(user.getEmail(), user.getMdp());
            return new ResponseEntity<>("User verification successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error verification user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
