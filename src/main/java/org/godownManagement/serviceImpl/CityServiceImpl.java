package org.godownManagement.serviceImpl;

import org.godownManagement.entities.City;
import org.godownManagement.repository.CityRepository;
import org.godownManagement.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> getAllCityDetails() {
        return cityRepository.findAll();
    }
}
