package org.godownManagement.repository;

import org.godownManagement.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntriesRepository extends JpaRepository<Entry, Integer> {

    @Query(value = "SELECT * FROM ENTRIES entry where entry.godown.godownId = :godownId",
            name = "GetAllEntriesInGodown", nativeQuery = true)
    List<Entry> getAllEntriesInGodown(@Param("godownId") int godownId);
}
