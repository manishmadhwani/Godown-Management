package org.godownManagement.repository;

import org.godownManagement.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query(name = "GetCityByCityNameAndStateName",
            value = "SELECT * FROM CITIES city WHERE city.name= :cityName AND city.state= :stateName", nativeQuery = true)
    City getCityByCityNameAndStateName(@Param("cityName") String name, @Param("stateName") String state);
}
