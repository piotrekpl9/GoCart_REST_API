package com.pluta.gocart.module.User.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pluta.gocart.module.Cart.model.Cart;
import com.pluta.gocart.module.Friend.model.Friend;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table( name = "users_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String number;
    @NotNull
    @Email
    private String email;
    @NotNull
    private Boolean activated;
    @NotNull
    private Date createdAt;

    @OneToOne(mappedBy = "owner")

    private Cart cart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Friend> friends;

    private Date updatedAt;
    private Date deletedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(int id, @NotBlank String username, @NotBlank String password, @NotNull String number, @NotNull @Email String email, @NotNull Boolean activated, @NotNull Date createdAt, Cart cart, List<Friend> friends, Date updatedAt, Date deletedAt, Set<Role> roles) {
        Id = id;
        this.username = username;
        this.password = password;
        this.number = number;
        this.email = email;
        this.activated = activated;
        this.createdAt = createdAt;
        this.cart = cart;
        this.friends = friends;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.roles = roles;
    }

    public User(int id, @NotBlank String username, @NotBlank String password, @NotNull String number, @NotNull Boolean activated, @NotNull Date createdAt, Cart cart, List<Friend> friends, Date updatedAt, Date deletedAt, Set<Role> roles) {
        Id = id;
        this.username = username;
        this.password = password;
        this.number = number;
        this.activated = activated;
        this.createdAt = createdAt;
        this.cart = cart;
        this.friends = friends;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.roles = roles;
    }

    public User(@NotBlank String username, @NotBlank String password, @NotNull String email, @NotNull Boolean activated, @NotNull Date createdAt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activated = activated;
        this.createdAt = createdAt;
    }

    public User(@NotBlank String username, @NotBlank String password, @NotNull String email, @NotNull Boolean activated, @NotNull Date createdAt, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activated = activated;
        this.createdAt = createdAt;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(@NotBlank String username, @NotBlank String password, @NotNull String number, @NotNull Boolean activated, @NotNull Date createdAt, Set<Role> roles, String email) {
        this.username = username;
        this.password = password;
        this.number = number;
        this.activated = activated;
        this.createdAt = createdAt;
        this.roles = roles;
        this.email = email;
    }
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public User(int id, @NotBlank String username, @NotBlank String password, @NotNull String number, @NotNull Boolean activated, @NotNull Date createdAt, List<Friend> friends, Date updatedAt, Date deletedAt) {
        Id = id;
        this.username = username;
        this.password = password;
        this.number = number;
        this.activated = activated;
        this.createdAt = createdAt;
        this.friends = friends;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
}
