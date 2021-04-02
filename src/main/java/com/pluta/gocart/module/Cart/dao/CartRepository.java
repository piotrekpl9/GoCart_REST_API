package com.pluta.gocart.module.Cart.dao;

import com.pluta.gocart.module.Cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
