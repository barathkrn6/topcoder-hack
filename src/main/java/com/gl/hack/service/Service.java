package com.gl.hack.service;

import com.gl.hack.model.User;

import java.util.List;

public interface Service {
    List<User> searchUsers(String query);
}
