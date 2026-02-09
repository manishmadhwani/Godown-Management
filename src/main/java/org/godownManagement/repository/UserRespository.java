package org.godownManagement.repository;

import org.godownManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<User, Integer> {

    @Query(name = "GetUserByContactNo", nativeQuery = true, value = "SELECT * FROM USERS u where u.CONTACT_NO= :contactNo")
    Optional<User> getUserByContactNo(@Param("contactNo") String contactNo);

    @Query(name = "GetUserByUserName", nativeQuery = true, value = "SELECT * FROM USERS u where u.USER_NAME= :userName")
    User getUserByUserName(@Param("userName") String userName);
}
