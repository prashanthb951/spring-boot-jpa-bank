package com.bank.repository;

import com.bank.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDAO,Long> {

    @Query("select user from UserDAO user where user.username=:name")
    Optional<UserDAO> findByUserName(@Param("name") String name);

}
