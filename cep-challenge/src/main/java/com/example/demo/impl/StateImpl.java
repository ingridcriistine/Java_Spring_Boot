package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.State;
import com.example.demo.repositories.StateRepository;
import com.example.demo.services.StateService;

public class StateImpl implements StateService {

    @Autowired
    StateRepository repo;

    @Override
    public State addState(String state, long code) {
        var statesName = repo.findByState(state);
        var statesCode = repo.findByCode(code);

        if(!statesName.isEmpty() || !statesCode.isEmpty()) {
            return null;
        }

        var newState = new State();
        newState.setState(state);
        newState.setCode(code);
        newState.setNextCode(code+1);
        repo.saveAndFlush(newState);

        return newState;
    }

    @Override
    public Long countStates() {
        return repo.count();
    }

    @Override
    public Long getNextCode() {
        var lastState = countStates();
        var state = repo.findById(lastState);
        var nextCode = state.get().getNextCode();

        return nextCode;
    }

    @Override
    public List<State> getStates(long page, long count) {
        
        var allStates = repo.findAll();

        if(count == -1) {
            return allStates;
        }

            // .stream()
            // .map(c -> new CitiesData(c.getName(), c.getState(), c.getCountry()))
            // .collect(Collectors.toList());

        return allStates;
    }

    @Override
    public Boolean updateState(long id, String state, long code) {
        var states = repo.findById(id);
        var newCode = repo.findByCode(code);
        var newState = repo.findByState(state);

        if(states.isEmpty()) {
            return false;
        }

        if(!state.equals(null) && newState.isEmpty()) {
            states.get(0).setState(state);
        }

        if(code != 0 && newCode.isEmpty()) {
            states.get(0).setCode(code);
            states.get(0).setNextCode(code+1);
        }

        repo.saveAndFlush(states.get(0));

        return true;
    }

    @Override
    public Boolean updateAllState(long id, String state, long code) {
        var states = repo.findById(id);
        var newCode = repo.findByCode(code);
        var newState = repo.findByState(state);

        if(states.isEmpty()) {
            return false;
        }

        if(newState.isEmpty()) {
            states.get(0).setState(state);
        }

        if(newCode.isEmpty()) {
            states.get(0).setCode(code);
            states.get(0).setNextCode(code+1);
        }

        repo.saveAndFlush(states.get(0));

        return true;
    }

    @Override
    public Boolean deleteState(long id) {
        var state = repo.findById(id);

        if(state.isEmpty()) {
            return false;
        }

        repo.delete(state.get(0));
        return true;
    }

    
    
}
