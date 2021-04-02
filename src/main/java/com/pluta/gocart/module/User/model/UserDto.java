package com.pluta.gocart.module.User.model;

import com.pluta.gocart.module.Friend.model.Friend;

import java.util.Date;
import java.util.Set;

public class UserDto {

    private String username;
    private String number;
    private Date createdAt;
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserDto(String username, String number, Date createdAt) {
        this.username = username;
        this.number = number;
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto() {
    }

}
