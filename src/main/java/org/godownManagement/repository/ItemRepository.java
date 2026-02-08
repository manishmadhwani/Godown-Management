package org.godownManagement.repository;

import org.godownManagement.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(nativeQuery = true, name = "GetAllItemsPerUser", value = "SELECT * FROM ITEMS items where items.owner.userName = :userName")
    List<Item> getAllItemsPerUser(@Param("userName") String userName);
}
