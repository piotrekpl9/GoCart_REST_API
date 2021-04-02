package com.pluta.gocart.module.Friend.payload.request;

public class FriendRequest {

    private int userId;
    private int friendId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public FriendRequest() {
    }

    public FriendRequest(int userId, int friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}
