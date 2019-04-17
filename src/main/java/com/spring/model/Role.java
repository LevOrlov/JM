package com.spring.model;

import org.springframework.security.core.GrantedAuthority;


public class Role implements GrantedAuthority {

    private Integer id;

    private String name;

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }


}
