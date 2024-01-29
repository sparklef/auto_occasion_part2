package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Utilisateur_site;
import com.c_project.auto_occasion.services.Utilisateur_siteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class Utilisateur_siteController {
    @Autowired
    private Utilisateur_siteService utilisateur_siteService;

    // Create user
    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody Utilisateur_site newUser) {
        try {
            utilisateur_siteService.create(newUser);
            //int id_lastusercreated=utilisateur_siteService.getLastCreatedUser();
            //String token_creation = utilisateur_siteService.generateToken(newUser.getEmail(), newUser.getMdp());
            //utilisateur_siteService.saveToken(token_creation, id_lastusercreated);
            //String token = utilisateur_siteService.getTokenUser(id_lastusercreated);
            return new ResponseEntity<>("Utilisateur créé", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // verification connexion user
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
    // avoir tous les utilisateurs
    @GetMapping("/allUser")
    public ResponseEntity<List<Utilisateur_site>> getAllUser() {
        try {
            List<Utilisateur_site> users = utilisateur_siteService.findAllUser();
            if (users != null) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // find one user by id
    @GetMapping("/findOne/{id_user}")
    public ResponseEntity<Utilisateur_site> getAdminById(@PathVariable int id_user) {
        try {
            Utilisateur_site user = utilisateur_siteService.findOne(id_user);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //find token
    /* 
    @GetMapping("/findToken/{token_user}")
    public ResponseEntity<Utilisateur_site> getTokenUser(@PathVariable String token_user){
        try {
            Utilisateur_site user=utilisateur_siteService.findToken(token_user);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

        
    @GetMapping("/findToken")
    public ResponseEntity<Utilisateur_site> getTokenUser(@RequestHeader("Authorization")  String token_user){
        String [] tab=token_user.split(" ");
        try {
            Utilisateur_site user=utilisateur_siteService.findToken(tab[1]);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
