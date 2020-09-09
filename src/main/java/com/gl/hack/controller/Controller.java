package com.gl.hack.controller;

import com.gl.hack.model.User;
import com.gl.hack.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/search-users")
    public List<User> searchUsers(@RequestParam(value = "query") String query) {
        return service.searchUsers(query);
    }
}
