package org.godownManagement.repository;

import org.godownManagement.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT item FROM Item item where item.comodity = :comodity and item.type= :type and item.markaName= :marka and item.packing= :packing")
    Item getItemByMarkaAndComodityAndPacking(@Param("comodity") String comodity, @Param("type") String type, @Param("marka") String marka, @Param("packing") int packing);
}
