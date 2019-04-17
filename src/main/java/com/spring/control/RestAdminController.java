package com.spring.control;

import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@RestController
public class RestAdminController {

    private final UserService userService;

    @Autowired
    public RestAdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/getall", method = RequestMethod.POST)
    public ResponseEntity getAll() {
        return new ResponseEntity<>((ArrayList) userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public ResponseEntity editId(@PathVariable(required = false, name = "id") int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ResponseEntity edit(@ModelAttribute User user) {
        User tempUser = userService.getUserById(user.getId());
        tempUser.setName(user.getName());
        tempUser.setLogin(user.getLogin());
        tempUser.setPassword(user.getPassword());
        userService.updateUser(tempUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/admin"));
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteUser(@PathVariable(required = false, name = "id") int id) {
        userService.deleteUser(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/admin"));
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public ResponseEntity addModal(@ModelAttribute(value = "user") User user) {
        userService.addUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/admin"));
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
