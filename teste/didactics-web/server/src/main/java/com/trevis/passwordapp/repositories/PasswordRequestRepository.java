package com.trevis.passwordapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.passwordapp.model.PasswordRequest;

@Repository
public interface PasswordRequestRepository extends JpaRepository<PasswordRequest, Long> {
    
}