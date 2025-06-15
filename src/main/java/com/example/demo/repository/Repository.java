package com.example.demo.repository;

import com.example.demo.model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface Repository extends JpaRepository<Users, Long> {
    UserDetails findByLogin(String role);
}
