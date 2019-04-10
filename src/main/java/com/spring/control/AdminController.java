package com.spring.control;


import com.spring.model.User;
import com.spring.service.UserServiсe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class    AdminController {

    @Autowired
    private UserServiсe UserServiceImpl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String notesList(Model model) {
        model.addAttribute("addUserObject", new User());
        return "thumhome";
    }
//todo куда отправляют ajax  запросы

  /*  @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute User user) {
        User tempUser = UserServiceImpl.getUserById(user.getId());
        tempUser.setName(user.getName());
        tempUser.setLogin(user.getLogin());
        tempUser.setPassword(user.getPassword());
        UserServiceImpl.updateUser(tempUser);
        return "redirect:/";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        System.out.println("logout()");
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable(required = false, name = "id") int id) {
        UserServiceImpl.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addModal(@ModelAttribute(value = "user") User user) {
        UserServiceImpl.addUser(user);
        return "redirect:/";
    }*/
}
