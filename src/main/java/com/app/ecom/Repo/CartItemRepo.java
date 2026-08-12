package com.app.ecom.Repo;

import com.app.ecom.Model.CartItem;
import com.app.ecom.Model.Product;
import com.app.ecom.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);
    Long deleteByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);
}
