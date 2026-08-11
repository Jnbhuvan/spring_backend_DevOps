package com.app.ecom.Controller;

import com.app.ecom.Model.CartItem;
import com.app.ecom.Services.CartItemService;
import com.app.ecom.dto.CartItemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/cartitems")
    public ResponseEntity<?> addToCart(@RequestHeader("X-User-ID") String userId,
                                       @RequestBody CartItemRequest cartItemRequest)
    {   return new ResponseEntity<>(HttpStatus.OK);
    }

}
