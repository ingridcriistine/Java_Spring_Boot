package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Whell")
    private Integer whellCount;
    
    @Column(name="Type")
    private String type;

    public Integer getWhellCount() {
        return whellCount;
    }

    public void setWhellCount(Integer whellCount) {
        this.whellCount = whellCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
