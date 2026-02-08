package org.godownManagement.repository;

import org.godownManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Integer> {

    @Query(name = "GetUserByContactNo", nativeQuery = true, value = "SELECT * FROM USER user where user.contactNo= :contactNo")
    Optional<User> getUserByContactNo(@Param("contactNo") Long contactNo);

    @Query(name = "GetUserByUserName", nativeQuery = true, value = "SELECT * FROM USER user where user.userName= :userName")
    User getUserByUserName(@Param("userName") String userName);
}
