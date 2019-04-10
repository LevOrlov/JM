package com.spring.control;

import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RestAdminController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/admin/getall", method = RequestMethod.POST)
    public ResponseEntity<ArrayList> getSearchResultViaAjax() {
        ResponseEntity<ArrayList> users = userService.getAllUsers1();
        return users;
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> editId(@PathVariable(required = false, name = "id") int id) {
        ResponseEntity<User> tempUser = userService.getUserById1(id);
        ResponseEntity<User> user = tempUser;
        return user;
    }
}
