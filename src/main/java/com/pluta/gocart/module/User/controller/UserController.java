package com.pluta.gocart.module.User.controller;

import com.pluta.gocart.module.Security.service.UserDetailsImpl;
import com.pluta.gocart.module.User.dao.UserRepository;
import com.pluta.gocart.module.User.model.User;
import com.pluta.gocart.module.User.model.UserDto;
import com.pluta.gocart.module.User.payload.request.UpdateRequest;
import com.pluta.gocart.module.User.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PutMapping(path = "/update")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateRequest request) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Something went wrong!"));

        User preUser = new UserUtils().clone(user);

        if(!Objects.isNull(request.getUsername()))
            user.setUsername(request.getUsername());
        if(!Objects.isNull(request.getPassword()))
            user.setPassword(encoder.encode(request.getPassword()));
        if(!Objects.isNull(request.getEmail()))
            user.setEmail(request.getPassword());
        if(!Objects.isNull(request.getActivated()))
            user.setActivated(request.getActivated());
        if(!Objects.isNull(request.getNumber()))
            user.setNumber(request.getNumber());

        if(Objects.equals(user,preUser)) {
            return ResponseEntity.badRequest().body("You have to provide some data to update!");
        }

        user.setUpdatedAt(new Date());
        userRepository.save(user);

        return ResponseEntity.ok().body("User successfully updated!");
    }
    @GetMapping(path = "/get")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> getUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Something went wrong!"));

        return ResponseEntity.ok().body(user);
    }

    @GetMapping(path = "/getUser")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> getSpecificUser(@RequestParam String name) {
        Optional<User> possibleUser = userRepository.findByUsername(name);
        if(possibleUser.isEmpty()) return ResponseEntity.badRequest().body("User with this name doesnt exist");
        User user = possibleUser.get();
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setNumber(user.getNumber());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setRoles(user.getRoles());
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping(path = "/delete")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> deleteUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new RuntimeException("Something went wrong!"));
        user.setDeletedAt(new Date());
        userRepository.save(user);

        return ResponseEntity.ok().body("Successfully deleted user");
    }
}
