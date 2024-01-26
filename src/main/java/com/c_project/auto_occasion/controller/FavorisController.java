package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Annonce;
import com.c_project.auto_occasion.model.Utilisateur_site;
import com.c_project.auto_occasion.services.FavorisService;
import com.c_project.auto_occasion.services.Utilisateur_siteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/favoris")
public class FavorisController {
    @Autowired
    private FavorisService favorisService;
    private Utilisateur_siteService utilisateur_siteService;

    @Autowired
    public FavorisController(Utilisateur_siteService utilisateur_siteService) {
        this.utilisateur_siteService = utilisateur_siteService;
    }
    
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

    @GetMapping("/favorisUser")
    public ResponseEntity<List<Annonce>> getListeFavoris(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader == null || authorizationHeader.isEmpty()) {
                return new ResponseEntity<>( HttpStatus.UNAUTHORIZED );
            }
            Utilisateur_site user=utilisateur_siteService.findToken(authorizationHeader);
            int id_user = user.getIdUser();
            List<Annonce> annonces =favorisService.allUserSFavoris(id_user);
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
