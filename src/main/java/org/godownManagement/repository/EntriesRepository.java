package org.godownManagement.repository;

import org.godownManagement.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntriesRepository extends JpaRepository<Entry, Integer> {

    @Query(value = "SELECT * FROM ENTRIES entry where entry.godown.godownId = :godownId",
            name = "GetAllEntriesInGodown")
    List<Entry> getAllEntriesInGodown(@Param("godownId") int godownId);
}
