package org.godownManagement.repository;

import org.godownManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRespository extends JpaRepository<User, Integer> {

    @Query(name = "GetUserByContactNo", nativeQuery = true, value = "SELECT * FROM USER user where user.contactNo= :contactNo")
    User getUserByContactNo(Long contactNo);
}
