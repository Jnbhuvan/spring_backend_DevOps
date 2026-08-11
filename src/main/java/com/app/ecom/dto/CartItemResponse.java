package com.app.ecom.dto;


import com.app.ecom.Model.Product;
import com.app.ecom.Model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {

    private Long prodId;

     private Integer quantity;
}
