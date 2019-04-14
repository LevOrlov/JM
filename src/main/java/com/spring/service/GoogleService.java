package com.spring.service;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GoogleService {

    private OAuth20Service service;
    private final String API_KEY = "348128802183-9si4susaehbqpqha53cfese2r6jt0dev.apps.googleusercontent.com";
    private final String API_SECRET = "Rr_RM0rEF5ajCqBuJnePe_-T";
    private final String SCOPE = "https://www.googleapis.com/auth/userinfo.email";
    private final String CALLBACK = "http://localhost:8090/auth/google";

    @PostConstruct
    private void init(){
        this.service  = new ServiceBuilder(API_KEY)
                .apiSecret(API_SECRET)
                .scope(SCOPE)
                .callback(CALLBACK)
                .build(GoogleApi20.instance());
    }



    public OAuth20Service getService() {
        return service;
    }
    public void customAuth(String json){

    }
}
