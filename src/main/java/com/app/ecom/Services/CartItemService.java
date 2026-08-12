package com.app.ecom.Services;

import com.app.ecom.Model.CartItem;
import com.app.ecom.Model.Product;
import com.app.ecom.Model.User;
import com.app.ecom.Repo.CartItemRepo;
import com.app.ecom.Repo.ProductRepo;
import com.app.ecom.Repo.UserRepo;
import com.app.ecom.dto.CartItemRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    CartItemRepo cartItemRepo;

    ProductRepo productRepo;

    UserRepo userRepo;

    public CartItemService(CartItemRepo cartItemRepo, ProductRepo productRepo, UserRepo userRepo) {
        this.cartItemRepo = cartItemRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public List<CartItem> getAllCart(){
        return cartItemRepo.findAll();
    }

    public boolean addCartItems(String userId, CartItemRequest cartItemRequest){

        Optional<Product> productOpt = productRepo.findById(cartItemRequest.getProdId());

        if(productOpt.isEmpty()){
            return false;
        }

        Product product = productOpt.get();

        if(product.getStockQuantity() <= 0){
            return false;
        }

        Optional<User> userOptional = userRepo.findById(Integer.valueOf(userId));

        if(userOptional.isEmpty()){
            return false;
        }

        User user = userOptional.get();

        CartItem existingCartItem = cartItemRepo.findByUserAndProduct(user, product);

        if(existingCartItem != null){
            //update product
            existingCartItem.setQuantity(existingCartItem.getQuantity()+ cartItemRequest.getQuantity());
            existingCartItem.setPrice(existingCartItem.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepo.save(existingCartItem);
        }

        else{
            //create a new CartItem
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(cartItemRequest.getQuantity());
            //product.setStockQuantity(product.getStockQuantity() - cartItemRequest.getQuantity());
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setPrice(product.getPrice());
            cartItemRepo.save(cartItem);
            //Product save = productRepo.save(product);
        }
        return true;
    }

    @Transactional
    public boolean deleteCartItem(String userId, Long prodId) {
        Optional<Product> productOptional = productRepo.findById(prodId);
        if(productOptional.isEmpty()){
            return false;
        }
        Product product = productOptional.get();

        Optional<User> userOptional = userRepo.findById(Integer.valueOf(userId));

        if(userOptional.isEmpty()){
            return false;
        }

        User user = userOptional.get();

      return cartItemRepo.deleteByUserAndProduct(user, product)>0;

    }
}
