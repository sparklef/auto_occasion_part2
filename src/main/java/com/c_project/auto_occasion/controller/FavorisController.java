package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Annonce;
import com.c_project.auto_occasion.model.Categorie;
import com.c_project.auto_occasion.model.Favoris;
import com.c_project.auto_occasion.services.FavorisService;
import com.c_project.auto_occasion.services.Utilisateur_siteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/favoris")
public class FavorisController {
    @Autowired
    private FavorisService favorisService;
    // liste des favoris d'un utilisateur pour l'admin
    @GetMapping("/listefavoris/{idUser}")
    public ResponseEntity<List<Annonce>> getFavorisId(@PathVariable int idUser){
        try {
            List<Annonce> liste = favorisService.allUserSFavoris(idUser);
            if (liste.size() != 0) {
                return new ResponseEntity<>(liste, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // liste des favoris de l'utilisateur pour l'utilisateur
    @GetMapping("/liste_favoris_user/{idUser}")
    public ResponseEntity<List<Annonce>> getFavorisSUserUsingToken(@RequestHeader("Authorization") String token_user,@PathVariable int idUser){
        String [] tab=token_user.split(" ");
        try {
            if (token_user == null || token_user.isEmpty()) {
                return new ResponseEntity<>( HttpStatus.UNAUTHORIZED );
            }
            List<Annonce> liste = favorisService.allUserSFavoris(idUser);
            if (liste.size() != 0) {
                return new ResponseEntity<>(liste, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // liste de tous les favoris
    @GetMapping("/listefavoris")
    public ResponseEntity<List<Favoris>> allFavoris(){
        try {
            List<Favoris> liste_favoris = favorisService.allFav();
            if (liste_favoris.size() != 0) {
                return new ResponseEntity<>(liste_favoris, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // creation d'un favoris
    @PostMapping("/create_favoris/{id_user}/{id_annonce}")
    public ResponseEntity<String> creation_favoris(@RequestHeader("Authorization") String authorizationHeader,@PathVariable int id_annonce, @PathVariable int id_user) {
        try {
            if (authorizationHeader == null || authorizationHeader.isEmpty()) {
                return new ResponseEntity<>("Authorization header is missing", HttpStatus.UNAUTHORIZED);
            }
            favorisService.createFav(id_annonce, id_user);
            return new ResponseEntity<>("Favoris created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // delete favoris
    @DeleteMapping("/delete_favoris/{id_favoris}")
    public ResponseEntity<Void> deleteFavoris(@RequestHeader("Authorization") String authorizationHeader,@PathVariable int id_favoris) {
        try {
            if (authorizationHeader == null || authorizationHeader.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            favorisService.deleteFav(id_favoris);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
