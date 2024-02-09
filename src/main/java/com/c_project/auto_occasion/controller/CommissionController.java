package com.c_project.auto_occasion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c_project.auto_occasion.model.Commission;
import com.c_project.auto_occasion.services.CommissionService;

@RestController
@RequestMapping("/api/commission")
@CrossOrigin
public class CommissionController {
    @Autowired
    CommissionService commissionService;

    
    @PostMapping("/insert")
    public ResponseEntity<String> insertion(@RequestBody Commission commission) {
        try {
            // Appeler la méthode create de commissionService qui retourne maintenant un double
            double chiffreAffaire = commissionService.create(commission);
            // Inclure le chiffre d'affaires calculé dans la réponse
            return new ResponseEntity<>("Commission created successfully. Chiffre d'affaires: " + chiffreAffaire, HttpStatus.CREATED);
        } catch (Exception e) {
            // Loggez l'exception pour obtenir plus d'informations
            System.err.println("An error occurred while creating commission: " + e.getMessage());
            // Retourner une réponse avec un statut d'erreur et un message d'erreur
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

  


}
