package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class State {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="state")
    private String state;
    
    @Column(name="nextCode")
    private long nextCode;

    @Column(name="code")
    private long code;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getNextCode() {
        return nextCode;
    }

    public void setNextCode(long nextCode) {
        this.nextCode = nextCode;
    }
}
