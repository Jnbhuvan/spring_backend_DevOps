package com.app.ecom.dto;


import com.app.ecom.Model.OrderItem;
import com.app.ecom.Model.OrderStatus;
import com.app.ecom.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponse {

    private Long id;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private List<OrderItemDTO> items;

    private LocalDateTime createdAt;


    public <R> OrderResponse(Long id, BigDecimal amount, OrderStatus status, Stream<R> rStream) {
    }
}
