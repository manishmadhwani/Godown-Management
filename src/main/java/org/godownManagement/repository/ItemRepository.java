package org.godownManagement.repository;

import org.godownManagement.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(name = "GetAllItemsPerUser", value = "SELECT * FROM ITEMS items where items.owner.userName = :userName")
    List<Item> getAllItemsPerUser(@Param("userName") String userName);
}
