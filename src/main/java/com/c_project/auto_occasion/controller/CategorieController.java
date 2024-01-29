package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Categorie;
import com.c_project.auto_occasion.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorie")
//@CrossOrigin(origins = "*")
public class CategorieController {
    @Autowired
    CategorieService categorieService;

    @GetMapping("/allCategorie")
    public ResponseEntity<List<Categorie>> getAllCategories() {
        try {
            List<Categorie> liste = categorieService.findAll();
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

    @GetMapping("/categorieId/{idcategorie}")
    public ResponseEntity<Categorie> getCategorieId(@PathVariable int idcategorie){
        try {
            Categorie categorie=categorieService.findOne(idcategorie);
            if (categorie != null) {
                return new ResponseEntity<>(categorie, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertion(@RequestBody Categorie categorie) {
        try {
            categorieService.create(categorie);
            return new ResponseEntity<>("Categorie created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{idcategorie}")
    public ResponseEntity<String> update(@PathVariable int idcategorie, @RequestBody Categorie updated_categorie) {
        try {
            Categorie existingMarque = categorieService.findOne(idcategorie);
            if (existingMarque != null) {
                categorieService.update(idcategorie, updated_categorie.getCategorie());
                return new ResponseEntity<>("Categorie name updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Categorie not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating Categorie name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idcategorie}")
    public ResponseEntity<String> delete(@PathVariable int idcategorie) {
        try {
            categorieService.delete(idcategorie);
            return new ResponseEntity<>("Categorie deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
