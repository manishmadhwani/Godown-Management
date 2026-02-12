package org.godownManagement.serviceImpl;

import org.godownManagement.entities.City;
import org.godownManagement.repository.CityRepository;
import org.godownManagement.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> getAllCityDetails() {
        List<City> cities = cityRepository.findAll();
        cities.sort((o1, o2) -> {
            if (Objects.equals(o1.getState(), o1.getState())) return o1.getName().compareTo(o2.getName());
            return o1.getState().compareTo(o2.getState());
        });
        return cities;
    }
}
