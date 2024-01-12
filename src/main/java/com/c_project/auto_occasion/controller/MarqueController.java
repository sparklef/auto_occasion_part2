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
public class MarqueController {
    @Autowired
    private MarqueService marqueService;
    /*@GetMapping("/all")
    public List<Marque> getAllMarques() {
        try {
            return marqueService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
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
    // Create
    @PostMapping("/create/{marque}")
    public ResponseEntity<String> createMarque(@PathVariable String marque) {
        try {
            marqueService.create(marque);
            return new ResponseEntity<>("Marque created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating marque", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Update
    /*@PutMapping("/update/{id}")
    public void updateMarque(@PathVariable int id, @RequestBody Marque marque) {
        try {
            marqueService.update(marque.getMarque(), id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMarque(@PathVariable int id, @RequestBody Marque marque) {
        try {
            marqueService.update(marque.getMarque(), id);
            return new ResponseEntity<>("Marque updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating marque", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Delete
    /*@DeleteMapping("/delete/{id}")
    public void deleteMarque(@PathVariable int id) {
        try {
            marqueService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMarque(@PathVariable int id) {
        try {
            marqueService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Find One
    /*@GetMapping("/findOne/{id}")
    public Marque getMarqueById(@PathVariable int id) {
        try {
            return marqueService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
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

