package com.spring.control;


import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.model.UserAuthentication;
import com.spring.service.GoogleService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

//todo почитать про o2auth
@Controller
public class GoogleController {

    @Autowired
    UserService userService;
    @Autowired
    private GoogleService service;

    @GetMapping(value = "/me/google")
    public void me(HttpServletResponse response) {
        String auth = service.getService().getAuthorizationUrl();
        response.setHeader("Location", auth);
        response.setStatus(302);
    }

    @GetMapping(value = "/auth/google")
    public String google(@RequestParam String code, HttpServletResponse servletResponse) {

        try {
            OAuth2AccessToken token = service.getService().getAccessToken(code);
            OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v1/userinfo?alt=json");
            service.getService().signRequest(token, request);
            Response response = service.getService().execute(request);
            JsonObject jsonObject = new JsonParser().parse(response.getBody()).getAsJsonObject();
            service.createUser(jsonObject);
            SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(userService.getUserByLogin(jsonObject.get("email").toString().replace("\"", ""))));
            return "redirect:/user";

        } catch (Exception e) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return null;
    }

}
