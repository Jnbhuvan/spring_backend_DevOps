package com.app.ecom.Services;

import com.app.ecom.Model.CartItem;
import com.app.ecom.Repo.CartItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    CartItemRepo cartItemRepo;

    public CartItemService(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    public List<CartItem> getAllCart(){
        return cartItemRepo.findAll();
    }
}
