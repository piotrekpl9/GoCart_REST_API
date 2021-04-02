package com.pluta.gocart.module.Friend.dao;

import com.pluta.gocart.module.Friend.model.Friend;
import com.pluta.gocart.module.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Integer> {
    public ArrayList<Friend> findAllByUser(User user);
}
