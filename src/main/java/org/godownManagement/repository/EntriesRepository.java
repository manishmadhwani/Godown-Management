package org.godownManagement.repository;

import org.godownManagement.entities.Entry;
import org.godownManagement.entities.Godown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntriesRepository extends JpaRepository<Entry, Integer> {

    @Query(value = "SELECT * FROM ENTRIES entry where entry.godown.godownId = :godown.godownId", name = "GetAllEntriesInGodown")
    List<Entry> getAllEntriesInGodown(Godown godown);
}
