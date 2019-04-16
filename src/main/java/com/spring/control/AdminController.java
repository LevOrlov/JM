package com.spring.control;


import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String notesList(Model model) {
        model.addAttribute("addUserObject", new User());
        return "thumhome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("addUserObject", new User());
        return "login";
    }

//todo куда отправляют ajax  запросы

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute User user) {
        User tempUser = userService.getUserById(user.getId());
        tempUser.setName(user.getName());
        tempUser.setLogin(user.getLogin());
        tempUser.setPassword(user.getPassword());
        userService.updateUser(tempUser);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        System.out.println("logout()");
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(required = false, name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addModal(@ModelAttribute(value = "user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }
}
