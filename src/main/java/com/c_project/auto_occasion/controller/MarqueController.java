package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Marque;
import com.c_project.auto_occasion.services.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marque")
@CrossOrigin(origins = "*")
public class MarqueController {
    @Autowired
    private MarqueService marqueService;
    // all marque
    @GetMapping("/all")
    public ResponseEntity<List<Marque>> getAllMarques() {
        try {
            List<Marque> marques = marqueService.findAll();
            if (marques != null) {
                return new ResponseEntity<>(marques, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // creation marque
    @PostMapping("/create")
    public ResponseEntity<String> createMarque(@RequestBody Marque marque) {
        try {
            marqueService.create(marque);
            return new ResponseEntity<>("Marque created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating marque", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // update a car brand
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMarqueName(@PathVariable int id, @RequestBody Marque updateRequest) {
        try {
            Marque existingMarque = marqueService.findOne(id);
            if (existingMarque != null) {
                marqueService.update(updateRequest.getMarque(), id);
                return new ResponseEntity<>("Marque name updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Marque not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating marque name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // delete a brand of car
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMarque(@PathVariable int id) {
        try {
            marqueService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // find one brand by his id
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Marque> getMarqueById(@PathVariable int id) {
        try {
            Marque marque = marqueService.findOne(id);
            if (marque != null) {
                return new ResponseEntity<>(marque, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}