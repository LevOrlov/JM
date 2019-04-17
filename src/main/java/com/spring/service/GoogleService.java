package com.spring.service;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.JsonObject;
import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@PropertySource("classpath:application.properties")
public class GoogleService {

    @Value("${API_KEY}")
    private String API_KEY;
    @Value("${API_SECRET}")
    private String API_SECRET;
    @Value("${SCOPE}")
    private String SCOPE;
    @Value("${CALLBACK}")
    private String CALLBACK;
    private final UserService userService;
    private OAuth20Service service;

    @Autowired
    public GoogleService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        this.service = new ServiceBuilder(API_KEY)
                .apiSecret(API_SECRET)
                .scope(SCOPE)
                .callback(CALLBACK)
                .build(GoogleApi20.instance());
    }

    public OAuth20Service getService() {
        return service;
    }

    public void createUser(JsonObject jsonObject) {
        User userFromJson = new User();
        userFromJson.setLogin(jsonObject.get("email").toString().replace("\"", ""));
        userFromJson.setName(jsonObject.get("email").toString().replace("\"", ""));
        userService.addUser(userFromJson);

    }

}
