package com.spring.config;

import com.spring.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    @ Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //тут могут быть доп. проверки
        return authentication;
    }

    @ Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public Authentication trust(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Authentication trustedAuthentication = new CustomUserAuthentication(user, authentication.getDetails());
        authentication = authenticate(trustedAuthentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}