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

import com.c_project.auto_occasion.model.Detail_voiture;
import com.c_project.auto_occasion.services.Detail_voitureService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/detail")
public class Detail_voitureController {
    @Autowired
    private Detail_voitureService detail_voitureService;
      @GetMapping("/search/{keyword}")
      public ResponseEntity<List<Detail_voiture>> search(@PathVariable String keyword) {
          try {
              List<Detail_voiture> detail_voitures = detail_voitureService.search(keyword);
              if (detail_voitures != null) {
                  return new ResponseEntity<>(detail_voitures, HttpStatus.OK);
              } else {
                  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
              }
          } catch (Exception e) {
              e.printStackTrace();
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }
}
