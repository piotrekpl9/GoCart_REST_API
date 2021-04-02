package com.pluta.gocart.module.Friend.controller;

import com.pluta.gocart.module.Friend.dao.FriendRepository;
import com.pluta.gocart.module.Friend.model.Friend;
import com.pluta.gocart.module.Friend.payload.request.FriendRequest;
import com.pluta.gocart.module.Friend.payload.request.GetUserFriends;
import com.pluta.gocart.module.User.dao.UserRepository;
import com.pluta.gocart.module.User.model.User;
import com.pluta.gocart.module.User.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<? extends Object> createFriend(@Valid @RequestBody FriendRequest request) {
        Optional<User> possibleUser = userRepository.findById(request.getUserId());
        Optional<User> possibleFriend = userRepository.findById(request.getFriendId());

        if(possibleUser.isPresent() && possibleFriend.isPresent()) {
            User user = possibleUser.get();
            User friendUser = possibleFriend.get();

            Friend friend = new Friend(user,friendUser);
            Friend friend2 = new Friend(friendUser,user);
            friendRepository.save(friend);
            friendRepository.save(friend2);
            List<Friend> friends = new ArrayList<>();
            friends.add(friend);
            friends.add(friend2);
            return ResponseEntity.ok().body(friends);
        } else return ResponseEntity.badRequest().body("Źle");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getFriends(@RequestParam int id) {
        Optional<User> possibleUser = userRepository.findById(id);
        if(!possibleUser.isPresent()) return ResponseEntity.badRequest().body("nie");

        ArrayList<Friend> userFriends = friendRepository.findAllByUser(possibleUser.get());
        ArrayList<UserDto> users = new ArrayList<>();
        User usere;
        for(Friend friend : userFriends)
        {
            usere = friend.getFriend();
            users.add(new UserDto(
                    usere.getUsername(),
                    usere.getNumber(),
                    usere.getCreatedAt()
            ));
        }
        return ResponseEntity.ok().body(users);
    }

}
