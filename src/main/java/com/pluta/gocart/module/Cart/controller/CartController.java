package com.pluta.gocart.module.Cart.controller;

import com.pluta.gocart.module.Cart.dao.CartRepository;
import com.pluta.gocart.module.Cart.model.Cart;
import com.pluta.gocart.module.Cart.payload.request.CartRequest;
import com.pluta.gocart.module.Cart.payload.request.CartUpdateRequest;
import com.pluta.gocart.module.Product.dao.ProductRepository;
import com.pluta.gocart.module.Product.model.Product;
import com.pluta.gocart.module.Security.service.UserDetailsImpl;
import com.pluta.gocart.module.User.dao.UserRepository;
import com.pluta.gocart.module.User.model.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(path = "/cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path = "/create")
    public ResponseEntity<String> createCart(@Valid @RequestBody CartRequest req) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> possibleUser = userRepository.findById(userDetails.getId());
        if(!possibleUser.isPresent()) return ResponseEntity.badRequest().body("Zły użytkownik");
        Set<String> productsNames = req.getProducts();
        List<Product> products = new ArrayList<Product>();
        for(String name : productsNames) {
            Optional<Product> possibleProduct = productRepository.findByName(name);
            if(possibleProduct.isPresent()) {
                products.add(possibleProduct.get());
            }
        }
        if(products.size()<1) return ResponseEntity.badRequest().body("Złe produkty");
        Cart cart = new Cart();
        cart.setOwner(possibleUser.get());
        cart.setProducts(products);
        cart.setCreatedAt(req.getCreatedAt());
        cartRepository.save(cart);
        return ResponseEntity.ok().body("Stworzono wózek");
    }

    @GetMapping(path = "/get")
    public ResponseEntity<?> getCart(@RequestParam int id) {
        Optional<Cart> possibleCart = cartRepository.findById(id);
        if(!possibleCart.isPresent()) return ResponseEntity.badRequest().body("Złe id");
        return ResponseEntity.ok().body(possibleCart.get());
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateCart(@RequestBody CartUpdateRequest request) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> possibleUser = userRepository.findById(userDetails.getId());
        if(possibleUser.isEmpty()) return ResponseEntity.badRequest().body("No user");
        User user = possibleUser.get();
        Optional<Cart> possibleCart = cartRepository.findById(user.getCart().getId());
        if(possibleCart.isEmpty()) return ResponseEntity.badRequest().body("No cart");
        Cart cart = possibleCart.get();

        Set<String> productsNames = request.getProducts();
        List<Product> products = cart.getProducts();

        for (String name : productsNames) {
            Optional<Product> product = productRepository.findByName(name);
            if(product.isPresent()) {
                if(!products.contains(product.get()))
                    products.add(product.get());
            }
        }
        cart.setProducts(products);
        cart.setUpdatedAt(new Date());
        cartRepository.save(cart);
        return ResponseEntity.ok().body("Success");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCart() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        if(optionalUser.isEmpty()) return ResponseEntity.badRequest().body("No user");
        User user = optionalUser.get();
        cartRepository.delete(user.getCart());
        user.setCart(null);

        return ResponseEntity.ok().body("Deleted Cart");
    }
}
