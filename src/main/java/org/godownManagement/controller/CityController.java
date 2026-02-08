package org.godownManagement.controller;

import org.godownManagement.entities.City;
import org.godownManagement.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    ICityService iCityService;

    @GetMapping("/fetchAllCities")
    ResponseEntity<List<City>> getAllCitiesAndStates() {
        List<City> cities = iCityService.getAllCityDetails();
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }
}
