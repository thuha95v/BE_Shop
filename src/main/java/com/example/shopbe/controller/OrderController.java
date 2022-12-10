package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.OrderDTO;
import com.example.shopbe.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/orders/{orderId}")
  public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
    return VsResponseUtil.ok(orderService.getOrderById(orderId));
  }

  @GetMapping("/ordersUser/{userId}")
  public ResponseEntity<?> getOrderByUserId(@PathVariable Long userId) {
    return VsResponseUtil.ok(orderService.getOrderByUserId(userId));
  }

  @PostMapping("/orders")
  public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
    return VsResponseUtil.ok(orderService.createOrder(orderDTO));
  }

  @PatchMapping("/orders/{orderId}")
  public ResponseEntity<?> changeOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
    return VsResponseUtil.ok(orderService.changeOrder(orderId, orderDTO));
  }

  @DeleteMapping("/orders/{orderId}")
  public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
    return VsResponseUtil.ok(orderService.deleteOrder(orderId));
  }
}
