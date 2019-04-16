package com.spring.control;

import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ArrayList getSearchResultViaAjax() {
        return (ArrayList<User>) userService.getAllUsers();
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public User editId(@PathVariable(required = false, name = "id") int id) {
        return userService.getUserById(id);
    }
}
