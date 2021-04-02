package com.pluta.gocart.module.Cart.payload.request;

import com.pluta.gocart.module.Product.model.Product;

import java.util.List;
import java.util.Set;

public class CartUpdateRequest {
    public Set<String> getProducts() {
        return products;
    }

    public void setProducts(Set<String> products) {
        this.products = products;
    }

    public CartUpdateRequest() {
    }

    private Set<String> products;
}
