package com.spring.service;


import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserServiсe userService;

    @Autowired
    public MyUserDetailsService(UserServiсe userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getUserByLogin(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}

