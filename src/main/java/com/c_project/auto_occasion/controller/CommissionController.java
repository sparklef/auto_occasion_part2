package com.c_project.auto_occasion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
            commissionService.create(commission);
            return new ResponseEntity<>("Commission created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
