package com.example.demo.services;

import java.util.List;

import com.example.demo.model.State;

public interface StateService {
    State addState(String state, long code);
    Long countStates();
    Long getNextCode();
    List<State> getStates(long page, long count);
    Boolean updateState(long id, String state, long code);
    Boolean updateAllState(long id, String state, long code);
    Boolean deleteState(long id);
}
