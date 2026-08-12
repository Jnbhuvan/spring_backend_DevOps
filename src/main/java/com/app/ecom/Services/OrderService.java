package com.app.ecom.Services;

import com.app.ecom.Repo.OrderRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
}
