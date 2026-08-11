package com.app.ecom.Controller;

import com.app.ecom.Model.CartItem;
import com.app.ecom.Services.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CartItemController {

    CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/cartitems")
    public ResponseEntity<List<CartItem>> getAllCartItems(){
        return new ResponseEntity<>(cartItemService.getAllCart(), HttpStatus.OK);
    }

}
