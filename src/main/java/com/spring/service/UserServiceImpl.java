package com.spring.service;


import com.spring.model.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String URL_USERS = "http://localhost:8080/rest";
    private static final String URL_ADD = "http://localhost:8080/rest/admin/add";
    private static final String URL_EDIT = "http://localhost:8080/rest/admin/edit/";
    private static final String URL_DEL = "http://localhost:8080/rest/admin/delete/";
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static HttpHeaders getHeaders() {
        String plainCredentials = "admin:admin";
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HttpEntity<User> request = new HttpEntity<>(user, getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.exchange(URL_ADD, HttpMethod.POST, request, User.class);

    }

    @Override
    public void deleteUser(int userId) {
        HttpEntity<String> request = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        String deleteUser = URL_DEL + userId;
        ResponseEntity<ArrayList> response = restTemplate.exchange(deleteUser, HttpMethod.DELETE, request, ArrayList.class);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HttpEntity<User> request = new HttpEntity<>(user, getHeaders());
        String editUser = URL_EDIT + user.getId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.exchange(editUser, HttpMethod.POST, request, User.class);
    }

    @Override
    public List<User> getAllUsers() {
        HttpEntity<String> request = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList> response = restTemplate.exchange(URL_USERS, HttpMethod.GET, request, ArrayList.class);
        return response.getBody();
    }


    @Override
    public User getUserById(int userId) {
        HttpEntity<String> request = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        String getUser = "http://localhost:8080/rest/user/" + userId;
        ResponseEntity<User> response = restTemplate.exchange(getUser, HttpMethod.POST, request, User.class);
        return response.getBody();
    }


    @Override
    public User getUserByLogin(String login) {
        HttpEntity<String> request = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        String getUser = "http://localhost:8080/rest/getuser/" + login;
        ResponseEntity<User> response = restTemplate.exchange(getUser, HttpMethod.POST, request, User.class);
        return response.getBody();
    }
}
