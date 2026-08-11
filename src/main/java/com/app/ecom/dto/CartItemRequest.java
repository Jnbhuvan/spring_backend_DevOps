package com.app.ecom.dto;

import com.app.ecom.Model.Product;
import com.app.ecom.Model.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemRequest {

    private Long prodId;

    private Integer quantity;
}
