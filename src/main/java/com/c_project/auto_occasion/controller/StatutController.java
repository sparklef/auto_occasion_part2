package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Marque;
import com.c_project.auto_occasion.model.Statut;
import com.c_project.auto_occasion.services.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statut")
//@CrossOrigin(origins = "*")
public class StatutController {
    @Autowired
    private StatutService statutService;
    // pour avoir tout les status
    @GetMapping("/all")
    public ResponseEntity<List<Statut>> getAllStatuts() {
        try {
            List<Statut> statuts = statutService.findAllState();
            if (statuts != null) {
                return new ResponseEntity<>(statuts, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // creation d'un statut
    @PostMapping("/create")
    public ResponseEntity<String> createStatut(@RequestBody Statut statut) {
        try {
            statutService.create(statut);
            return new ResponseEntity<>("Marque created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating marque", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // update a statut
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStatutName(@PathVariable int id, @RequestBody Statut updateStatut) {
        try {
            Statut existingStatut = statutService.findOneState(id);
            if (existingStatut != null) {
                statutService.update(updateStatut.getStatut(), id);
                return new ResponseEntity<>("Statut name updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Statut not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating marque name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // delete a statut
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStatut(@PathVariable int id) {
        try {
            statutService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // find one statut by his id
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Statut> getStatutById(@PathVariable int id) {
        try {
            Statut statut = statutService.findOneState(id);
            if (statut != null) {
                return new ResponseEntity<>(statut, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
