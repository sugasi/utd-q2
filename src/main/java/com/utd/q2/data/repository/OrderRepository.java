package com.utd.q2.data.repository;

import com.utd.q2.data.entity.Order;
import com.utd.q2.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByOrderDateBetween(LocalDate from, LocalDate to);
    List<Order> findAllByOrderDateBetweenAndProduct(LocalDate from, LocalDate to, Product product);
}
