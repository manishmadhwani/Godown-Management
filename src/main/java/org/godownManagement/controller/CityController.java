package org.godownManagement.controller;

import org.godownManagement.entities.City;
import org.godownManagement.service.ICityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    ICityService iCityService;

    @GetMapping("/fetchAllCities")
    ResponseEntity<List<City>> getAllCitiesAndStates() {
        List<City> cities = iCityService.getAllCityDetails();
        logger.info("[getAllCitiesAndStates] Fetched all cities and sending to the ui: {}", cities.size());
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }
}
