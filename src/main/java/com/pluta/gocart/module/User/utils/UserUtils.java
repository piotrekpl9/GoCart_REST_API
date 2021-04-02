package com.pluta.gocart.module.User.utils;

import com.pluta.gocart.module.User.model.User;

public class UserUtils extends User{
    public User clone(User user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getNumber(),
                user.getEmail(),
                user.getActivated(),
                user.getCreatedAt(),
                user.getCart(),
                user.getFriends(),
                user.getUpdatedAt(),
                user.getDeletedAt(),
                user.getRoles());
    }
}
