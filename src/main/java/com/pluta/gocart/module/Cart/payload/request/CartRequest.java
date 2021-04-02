package com.pluta.gocart.module.Cart.payload.request;

import com.pluta.gocart.module.Product.model.Product;
import com.pluta.gocart.module.User.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

public class CartRequest {

    Set<String> products;
    private Date createdAt;

    public CartRequest( Date createdAt) {
        this.createdAt = createdAt;
    }

    public CartRequest( Set<String> products, Date createdAt) {
        this.products = products;
        this.createdAt = createdAt;
    }

    public CartRequest() {
    }

    public Set<String> getProducts() {
        return products;
    }

    public void setProducts(Set<String> products) {
        this.products = products;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
