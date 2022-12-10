package com.example.shopbe.repository;

import com.example.shopbe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("select o from Order o where o.id = ?1")
  Order findOrderById(Long id);

  @Query("select o from Order o where o.user.id = ?1")
  List<Order> findAllByUser_Id(Long userId);
}
