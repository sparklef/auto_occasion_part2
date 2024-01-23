package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Admin_site;
import com.c_project.auto_occasion.services.Admin_siteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class Admin_siteController {
    @Autowired
    private Admin_siteService adminSiteService;
    @GetMapping("/allAdmin")
    public ResponseEntity<List<Admin_site>> getAllAdmin() {
        try {
            List<Admin_site> admin = adminSiteService.findAll();
            if (admin != null) {
                return new ResponseEntity<>(admin, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findOne/{id_admin}")
    public ResponseEntity<Admin_site> getAdminById(@PathVariable int id_admin) {
        try {
            Admin_site admin = adminSiteService.findOne(id_admin);
            if (admin != null) {
                return new ResponseEntity<>(admin, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> createAdmin(@RequestBody Admin_site newAdmin) {
        try {
            adminSiteService.create(newAdmin);
            return new ResponseEntity<>("Admin created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating admin", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update_admin/{id_admin}")
    public ResponseEntity<String> updateAdmin(@RequestBody Admin_site update, @PathVariable int id_admin) {
        try {
            Admin_site existingAdmin = adminSiteService.findOne(id_admin);
            if (existingAdmin != null) {
                adminSiteService.update(update, id_admin);
                return new ResponseEntity<>("Admin updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Admin not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating admin", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id_admin}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id_admin) {
        try {
            adminSiteService.delete(id_admin);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/verif")
    public ResponseEntity<String> verifAdmin(@RequestBody Admin_site admin) {
        try {
            adminSiteService.verif_Admin(admin.getEmail(), admin.getMdp());
            return new ResponseEntity<>("User verification successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error verification user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/validate_annonce/{id_annonce}")
    public ResponseEntity<String> validerAnnonce(@PathVariable int id_annonce) {
        try {
            adminSiteService.validerAnnonce(id_annonce);
            return new ResponseEntity<>("Annonce validée", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de la validation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
