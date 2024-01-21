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

}
