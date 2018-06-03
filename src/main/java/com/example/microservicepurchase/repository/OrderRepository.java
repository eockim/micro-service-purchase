package com.example.microservicepurchase.repository;

import com.example.microservicepurchase.entity.Order;
import com.example.microservicepurchase.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    public Optional<Order> findByOrderId(String orderId);
    public List<Order> findByUserId(String userId);
}
