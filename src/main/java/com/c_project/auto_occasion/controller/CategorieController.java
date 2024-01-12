package com.c_project.auto_occasion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c_project.auto_occasion.model.Categorie;
import com.c_project.auto_occasion.model.Marque;
import com.c_project.auto_occasion.services.CategorieService;

@RestController
@RequestMapping("/api/categorie")
public class CategorieController {
    @Autowired
    CategorieService categorieService;

    @GetMapping("/allCategorie")
    public ResponseEntity<List<Categorie>> getAllCategories() {
        try {
            List<Categorie> liste = categorieService.getAllCategorie();
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
            Categorie categorie=categorieService.getCategorieSpecifique(idcategorie);
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

    @PostMapping("/insert/{categorie}")
    public ResponseEntity<String> insertion(@PathVariable String categorie) {
        try {
            categorieService.insertionCategorie(categorie);
            return new ResponseEntity<>("Categorie created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{idcategorie}/{categorie}")
    public ResponseEntity<String> update(@PathVariable int idcategorie, @PathVariable String categorie) {
        try {
            categorieService.updateCategorie(idcategorie, categorie);
            return new ResponseEntity<>("Categorie updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idcategorie}")
    public ResponseEntity<String> delete(@PathVariable int idcategorie) {
        try {
            categorieService.deleteCategorie(idcategorie);
            return new ResponseEntity<>("Categorie deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
