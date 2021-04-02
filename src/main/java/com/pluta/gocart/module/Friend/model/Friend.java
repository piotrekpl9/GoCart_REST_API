package com.pluta.gocart.module.Friend.model;

import com.pluta.gocart.module.User.model.User;

import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Friend_Id")
    private User friend;

    public Friend() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public Friend(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }
}
