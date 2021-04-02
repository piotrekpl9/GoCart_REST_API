package com.pluta.gocart.module.User.dao;

import com.pluta.gocart.module.User.model.ERole;
import com.pluta.gocart.module.User.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
