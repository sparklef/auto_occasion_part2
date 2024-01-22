package com.c_project.auto_occasion.controller;

import com.c_project.auto_occasion.model.Annonce;
import com.c_project.auto_occasion.model.Detail_voiture;
import com.c_project.auto_occasion.services.Detail_voitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail_voiture")
@CrossOrigin(origins = "*")
public class Detail_voitureController {
    @Autowired
    private Detail_voitureService detailVoitureService;
    @PostMapping("/create_detail")
    public ResponseEntity<String> createDetail(@RequestBody Detail_voiture newDetail) {
        try {
            detailVoitureService.createDetail(newDetail);
            return new ResponseEntity<>("Detail created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating detail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete_detail/{id_detail}")
    public ResponseEntity<Void> deleteDetail(@PathVariable int id_detail) {
        try {
            detailVoitureService.deleteDetail(id_detail);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update_detail/{id_detail}")
    public ResponseEntity<String> updateDetail(@RequestBody Detail_voiture update, @PathVariable int id_detail) {
        try {
            Detail_voiture existingDetail = detailVoitureService.getOneDetail(id_detail);
            if (existingDetail != null) {
                detailVoitureService.updateDetail(update, id_detail);
                return new ResponseEntity<>("Detail updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Detail not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating detail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all_detail")
    public ResponseEntity<List<Detail_voiture>> allDetails() {
        try {
            List<Detail_voiture> details = detailVoitureService.allDetails();
            if (details != null) {
                return new ResponseEntity<>(details, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/one_detail/{id_detail}")
    public ResponseEntity<Detail_voiture> oneDetail(@PathVariable int id_detail) {
        try {
            Detail_voiture detailVoiture = detailVoitureService.getOneDetail(id_detail);
            if (detailVoiture != null) {
                return new ResponseEntity<>(detailVoiture, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
