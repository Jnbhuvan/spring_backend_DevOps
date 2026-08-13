package com.app.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {

    private Long id;

    private Long productId;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal subtTotal;

    public OrderItemDTO(Long id, Integer quantity, BigDecimal price, BigDecimal multiply) {
    }
}
