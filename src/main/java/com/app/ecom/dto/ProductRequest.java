package com.app.ecom.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String productName;
    private String description;
    private Integer stockQuantity;
    private BigDecimal price;
    private String category;
    private String imageUrl;
    private Boolean active;
}
