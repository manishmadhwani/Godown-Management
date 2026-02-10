package org.godownManagement.repository;

import org.godownManagement.entities.Godown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GodownRepository extends JpaRepository<Godown, Integer> {

    @Query(value = "SELECT g FROM Godown g where g.owner.contactNo = :contactNo")
    List<Godown> getAllGodownsPerUser(@Param("contactNo") String contactNo);
}
