package com.spring.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("com.spring")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final AuthenticationSuccessHandler successHandler;

    @Autowired
    public SecurityConfig(AuthenticationSuccessHandler successHandler, @Qualifier("myUserDetailsService") UserDetailsService userDetailsService) {
        this.successHandler = successHandler;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/resources/**").permitAll();
        http.authorizeRequests().antMatchers("/static/**").permitAll();
        http.authorizeRequests().antMatchers("/login", "/me/google", "/auth/google").permitAll();
        http.authorizeRequests()
                .antMatchers("/admin/**", "/user").hasAuthority("ADMIN")
                .antMatchers("/user").hasAuthority("USER")
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/spring_check")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler).and()
                .logout();
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity security) {
        security.ignoring().antMatchers("/resources/**", "/static/**");
    }
}