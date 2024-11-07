package com.trevis.passwordapp.services;

import com.trevis.passwordapp.model.User;

public interface UserAuth {
    User loginByUsername(String username);
    User loginByEmail(String email);
}