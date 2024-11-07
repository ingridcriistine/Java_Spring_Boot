package com.trevis.passwordapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trevis.passwordapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
    List<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :loginValue or u.email = :loginValue")
    List<User> login(@Param("loginValue") String loginValue);
}