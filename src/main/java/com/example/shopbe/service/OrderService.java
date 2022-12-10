package com.example.shopbe.service;

import com.example.shopbe.dto.OrderDTO;
import com.example.shopbe.entity.Order;
import com.example.shopbe.payload.TrueFalseResponse;

import java.util.List;

public interface OrderService {
    Order getOrderById(Long id);
    List<Order> getOrderByUserId(Long userId);
    Order createOrder(OrderDTO orderDTO);
    Order changeOrder(Long id, OrderDTO orderDTO);
    TrueFalseResponse deleteOrder(Long id);
}
