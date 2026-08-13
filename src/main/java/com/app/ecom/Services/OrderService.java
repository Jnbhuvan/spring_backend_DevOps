package com.app.ecom.Services;

import com.app.ecom.Model.*;
import com.app.ecom.Repo.CartItemRepo;
import com.app.ecom.Repo.OrderRepo;
import com.app.ecom.Repo.UserRepo;
import com.app.ecom.dto.OrderItemDTO;
import com.app.ecom.dto.OrderResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    OrderRepo orderRepo;

    CartItemRepo cartItemRepo;

    UserRepo userRepo;

    CartItemService cartItemService;

    public OrderService(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    public OrderService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public OrderService(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public OrderResponse createOrder(String userId) {

        OrderResponse orderResponse = new OrderResponse();
        User user = userRepo.findById(Integer.valueOf(userId)).orElse(null);

        if(user == null){
            return null;
        }
        List<CartItem> cartItemList = cartItemRepo.findByUser(user);

        if(cartItemList.isEmpty()){
            return null;
        }
        BigDecimal totalAmt = cartItemList.stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setAmount(totalAmt);
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);

        List<OrderItem> orderItems = cartItemList.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                )).toList();
        order.setOrderItems(orderItems);

        Order savedOrder = orderRepo.save(order);

        cartItemService.clearCart(userId);

        return mapToOrderResponse(savedOrder);

    }

    private OrderResponse mapToOrderResponse(Order order) {
            return new OrderResponse(
                    order.getId(),
                    order.getAmount(),
                    order.getStatus(),
                    order.getOrderItems().stream()
                            .map( item ->
                                    new OrderItemDTO(
                                            item.getId(),
                                            item.getQuantity(),
                                            item.getPrice(),
                                            item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                            ).toList(),
                    order.getCreatedAt()
            );
    }
}
