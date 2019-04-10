package com.spring.control;

import com.spring.service.UserServiсe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
    @Autowired
    private UserServiсe UserServiceImpl;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView userPage() {
        ModelAndView model = new ModelAndView();
        String name = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            name = userDetail.getUsername();
            model.addObject("userProfile", UserServiceImpl.getUserByLogin(userDetail.getUsername()));
        }
        model.addObject("userProfile", UserServiceImpl.getUserByLogin(name));
        return model;
    }
}
