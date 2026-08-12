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
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId,
                                       @RequestBody CartItemRequest cartItemRequest)
    {   if(cartItemService.addCartItems(userId, cartItemRequest)){
        return new ResponseEntity<>("CartItem added successfully", HttpStatus.OK);
    }
    else{
        return new ResponseEntity<>("Failed to Add CartItem to DB", HttpStatus.BAD_REQUEST);
    }
    }


    @DeleteMapping("/cartItem/{prodId}")
    public ResponseEntity<String> deleteCartItem(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable Long prodId
    ){
        if(cartItemService.deleteCartItem(userId, prodId)) {
          return new ResponseEntity<>("CartItem deleted Succesfully!",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Failed to delete CartItem", HttpStatus.BAD_REQUEST);
        }
    }

}
