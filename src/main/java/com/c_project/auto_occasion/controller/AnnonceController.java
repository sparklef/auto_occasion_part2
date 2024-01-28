package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Annonce;
import com.c_project.auto_occasion.model.Utilisateur_site;
import com.c_project.auto_occasion.services.AnnonceService;
import com.c_project.auto_occasion.services.Utilisateur_siteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annonce")
@CrossOrigin(origins = "*")
public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;
    private Utilisateur_siteService utilisateur_siteService;

    @Autowired
    public AnnonceController(Utilisateur_siteService utilisateur_siteService) {
        this.utilisateur_siteService = utilisateur_siteService;
    }
      // Update status 
      @PutMapping("/update_statut_backoffice/{id}/{newState}")
      public ResponseEntity<String> updateStatut(@PathVariable int newState ,@PathVariable int id) {
          try {
             annonceService.updateStatut(newState, id);
             return new ResponseEntity<>("Annonce's state updated successfully", HttpStatus.OK);
          } catch (Exception e) {
              e.printStackTrace();
              return new ResponseEntity<>("Error updating Annonce's state", HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }
    //update statut with token
    @PutMapping("/update_statut/{id}/{newState}")
    public ResponseEntity<String> updateStatutOfUserToken(@RequestHeader("Authorization") String authorizationHeader,@PathVariable int newState ,@PathVariable int id) {
        try {
            if (authorizationHeader == null || authorizationHeader.isEmpty()) {
                return new ResponseEntity<>("Authorization header is missing", HttpStatus.UNAUTHORIZED);
            }
            annonceService.updateStatut(newState, id);
            return new ResponseEntity<>("Annonce's state updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating Annonce's state", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // mandeha io create annonce io
    @PostMapping("/create_annonce")
    public ResponseEntity<String> createAnnonce(@RequestHeader("Authorization") String authorizationHeader,@RequestBody Annonce newAnnonce) {
        String [] tab=authorizationHeader.split(" ");
        System.out.println(tab[1]);
          try {
              if (authorizationHeader == null || authorizationHeader.isEmpty()) {
                  return new ResponseEntity<>("Authorization header is missing", HttpStatus.UNAUTHORIZED);
              }
              Utilisateur_site user=utilisateur_siteService.findToken(tab[1]);
              newAnnonce.setIdUser(user.getIdUser());
              annonceService.create(newAnnonce);
              return new ResponseEntity<>("Annonce created successfully", HttpStatus.CREATED);
          } catch (Exception e) {
              e.printStackTrace();
              return new ResponseEntity<>("Error creating annonce", HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
    // delete annonce
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
    // toutes les annonces
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
    // les annonces d'un utilisateur
    @GetMapping("/annonces_of_user")
    public ResponseEntity<List<Annonce>> allAnnoncesOfAnUser(@RequestHeader("Authorization") String authorizationHeader) {
        String[]tab=authorizationHeader.split(" ");
        System.out.println(tab[1]);
        try {
            if (authorizationHeader == null || authorizationHeader.isEmpty()) {
                return new ResponseEntity<>( HttpStatus.UNAUTHORIZED );
            }
            Utilisateur_site user=utilisateur_siteService.findToken(tab[1]);
            int id_user = user.getIdUser();
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
    // une annonce d'un utilisateur
    @GetMapping("/one_annonce_user/{id_annonce}")
    public ResponseEntity<Annonce> oneUserAnnonce(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int id_annonce) {
        String[]tab=authorizationHeader.split(" ");
        System.out.println(tab[1]);
        try {
            if (authorizationHeader == null || authorizationHeader.isEmpty()) {
                return new ResponseEntity<>( HttpStatus.UNAUTHORIZED );
            }
            Utilisateur_site user=utilisateur_siteService.findToken(tab[1]);
            int id_user = user.getIdUser();
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

    // les annonces non validees
    @GetMapping("/annonces_non_validees")
    public ResponseEntity<List<Annonce>> annoncesNonValidees() {
        try {
            List<Annonce> annonces = annonceService.findAllAnnonceNonValidee();
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
    @GetMapping("/annonces_validees")
    public ResponseEntity<List<Annonce>> annoncesValidees() {
        try {
            List<Annonce> annonces = annonceService.findAllAnnoncesValidee();
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
    // annonces validees d'un user
    @GetMapping("/annonces_validees/{id_user}")
    public ResponseEntity<List<Annonce>> annoncesValideesOfUser(@PathVariable int id_user) {
        try {
            List<Annonce> annonces = annonceService.findAllAnnonceValideeOfUser(id_user);
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
    // annonces non validees d'un user
    @GetMapping("/annonces_non_validees/{id_user}")
    public ResponseEntity<List<Annonce>> annoncesNonValideesOfUser(@PathVariable int id_user) {
        try {
            List<Annonce> annonces = annonceService.findAllAnnonceNonValideeOfUser(id_user);
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
