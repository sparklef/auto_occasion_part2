package com.c_project.auto_occasion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c_project.auto_occasion.model.Voiture;
import com.c_project.auto_occasion.services.VoitureService;

@RestController
@RequestMapping("/api/voiture")
@CrossOrigin(origins = "*")
public class VoitureController {
    @Autowired
    private VoitureService voitureService;

    //find all
    @GetMapping("/all")
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        try {
            List<Voiture> voitures = voitureService.findall();
            if (voitures != null) {
                return new ResponseEntity<>(voitures, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //find one
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable int id) {
        try {
            Voiture voiture = voitureService.findone(id);
            if (voiture != null) {
                return new ResponseEntity<>(voiture, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
