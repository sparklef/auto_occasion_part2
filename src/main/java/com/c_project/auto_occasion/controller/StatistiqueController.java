package com.c_project.auto_occasion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c_project.auto_occasion.model.Stat;
import com.c_project.auto_occasion.model.Statistique;
import com.c_project.auto_occasion.services.StatistiqueService;

@RestController
@RequestMapping("/api/statistique")
//@CrossOrigin(origins = "*")
public class StatistiqueController {
    @Autowired
    private StatistiqueService statistiqueService;
    // statistique : total utilisateur, nombre annonce confirmée, total voiture, chiffre d'affaire
    @GetMapping("/stat")
    public ResponseEntity<Statistique> getStatistique() {
        try {
            Statistique stat = statistiqueService.FindStatistique();
            if (stat != null) {
                return new ResponseEntity<>(stat, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // toutes les statistiques
    @GetMapping("/statistiques")
    public ResponseEntity<List<Stat>> getAllStat(){
        try {
            List<Stat> liste = statistiqueService.FindStat();
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
}
