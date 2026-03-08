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

    @Query(value = "SELECT e FROM Entry e" +
            " where e.godown.godownId = :godownId and e.item.comodity= :comodity and e.item.type= :type" +
            " and e.item.markaName= :marka and e.item.packing= :packing")
    Entry checkIfItemExistInGodown(@Param("godownId") int godownId,
                                   @Param("comodity") String comodity,
                                   @Param("type") String type,
                                   @Param("marka") String marka,
                                   @Param("packing") int packing);
}
