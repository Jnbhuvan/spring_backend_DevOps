package com.app.ecom.dto;


import com.app.ecom.Model.OrderItem;
import com.app.ecom.Model.OrderStatus;
import com.app.ecom.Model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {

    private Long id;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private List<OrderItemDTO> items;

    private LocalDateTime createdAt;

}
