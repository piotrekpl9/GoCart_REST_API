package com.pluta.gocart.module.Friend.payload.request;

public class GetUserFriends {
    private int id;

    public GetUserFriends(int id) {
        this.id = id;
    }

    public GetUserFriends() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
