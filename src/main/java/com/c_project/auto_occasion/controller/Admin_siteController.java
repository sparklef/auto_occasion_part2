package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Admin_site;
import com.c_project.auto_occasion.services.Admin_siteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;

@RestController
@RequestMapping("/api/admin")
public class Admin_siteController {
    @Autowired
    private Admin_siteService adminSiteService_siteService;

    @PostMapping("/create")
    public ResponseEntity<String> createAdmin(@RequestBody Admin_site adminRequest) {
        try {
            adminSiteService_siteService.create(adminRequest.getEmail(),
                    adminRequest.getNom(),
                    adminRequest.getPrenom(),
                    adminRequest.getMdp(),
                    adminRequest.getContact());
            return new ResponseEntity<>("Admin created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating Admin", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
