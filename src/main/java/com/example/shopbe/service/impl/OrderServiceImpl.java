package com.example.shopbe.service.impl;

import com.example.shopbe.config.exception.VsException;
import com.example.shopbe.dto.OrderDTO;
import com.example.shopbe.dto.OrderDetailDto;
import com.example.shopbe.entity.*;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.*;
import com.example.shopbe.service.OrderService;
import com.example.shopbe.util.SecurityUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final BillDetailRepository billDetailRepository;
  private final ModelMapper modelMapper;
  private final OrderProductRepository orderProductRepository;
  private final ProductRepository productRepository;

  public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                          BillDetailRepository billDetailRepository, ModelMapper modelMapper,
                          OrderProductRepository orderProductRepository, ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.billDetailRepository = billDetailRepository;
    this.modelMapper = modelMapper;
    this.orderProductRepository = orderProductRepository;
    this.productRepository = productRepository;
  }

  @Override
  public Order getOrderById(Long id) {
    return orderRepository.findOrderById(id);
  }

  @Override
  public List<Order> getOrderByUserId(Long userId) {
    return orderRepository.findAllByUser_Id(userId);
  }

  @Override
  public Order createOrder(OrderDTO orderDTO) {
    BillDetail billDetail = billDetailRepository.save(modelMapper.map(orderDTO, BillDetail.class));
    Order order = new Order();
    order.setShipping(orderDTO.getShipping());
    order.setUser(getUserLogin());
    order.setBillDetail(billDetail);
    Order newOrder = orderRepository.save(order);
    billDetail.setOrder(newOrder);
    billDetailRepository.save(billDetail);

    for (OrderDetailDto dto : orderDTO.getLstProduct()) {
      orderProductRepository.save(new OrderProduct(dto.getQuality(), getProductById(order.getId()), newOrder));
    }

    return newOrder;
  }

  @Override
  public Order changeOrder(Long id, OrderDTO orderDTO) {
    Order order = orderRepository.findOrderById(id);
    if (order == null) {
      throw new VsException("Can not find order id " + id);
    }
    BillDetail billDetail = order.getBillDetail();
    billDetail.setCity(orderDTO.getCity());
    billDetail.setFirstName(orderDTO.getFirstName());
    billDetail.setLastName(orderDTO.getLastName());
    billDetail.setCompanyName(orderDTO.getCompanyName());
    billDetail.setStreet(orderDTO.getStreet());
    billDetail.setPostcode(orderDTO.getPostcode());
    billDetail.setPhone(orderDTO.getPhone());
    billDetail.setEmail(orderDTO.getEmail());
    billDetail.setOrderNotes(orderDTO.getOrderNotes());

    billDetailRepository.save(billDetail);
    order.setShipping(orderDTO.getShipping());

    return orderRepository.save(order);
  }

  @Override
  public TrueFalseResponse deleteOrder(Long id) {
    Order order = orderRepository.findOrderById(id);
    orderRepository.delete(order);
    return new TrueFalseResponse(true);
  }

  private User getUserLogin() {
    return userRepository.findUserById(SecurityUtil.getCurrentUserLogin());
  }

  private Product getProductById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isEmpty()) {
      throw new VsException("Can not find product by id " + id);
    }
    return product.get();
  }

}
