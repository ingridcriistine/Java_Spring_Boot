package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByState(String state);
    List<State> findByCode(long code);
    List<State> findById(long id);
} 
