package com.spring.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            switch (authority.getAuthority()) {
                case "USER":
                    try {
                        redirectStrategy.sendRedirect(arg0, arg1, "/user");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "ADMIN":
                    try {
                        redirectStrategy.sendRedirect(arg0, arg1, "/");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }
}
