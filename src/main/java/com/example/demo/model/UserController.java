package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
   
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<Users>>findAll(){
        List<Users> findAll = userService.getAllUsers();
        return ResponseEntity.ok().body(findAll);
    };
}
