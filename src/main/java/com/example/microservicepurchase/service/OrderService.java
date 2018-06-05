package com.example.microservicepurchase.service;

import com.example.microservicepurchase.entity.Order;
import com.example.microservicepurchase.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Async
    public CompletableFuture<Order> createOrder(Order order){
        return CompletableFuture.completedFuture(orderRepository.save(order));
    }

    public Iterable<Order> getOrders(){

        return Optional.<Iterable<Order>>ofNullable(orderRepository.findAll()).orElse(new ArrayList<>());

    }

    @Async
    public CompletableFuture<Order> getOrderByOrderId(String orderId){
        return CompletableFuture.completedFuture(orderRepository.findByOrderId(orderId).orElse(new Order()));
    }

    public Flux<Order> deleteOrder(String orderId){

        orderRepository.deleteById(orderId);

        return Flux.fromIterable(orderRepository.findAll());
    }

    @Async
    public CompletableFuture<Order> updateOrder(String orderId, Order order){

        Optional<Order> findOrder = orderRepository.findByOrderId(orderId);

        findOrder
                .ifPresent(x -> {
                    order.setOrderId(orderId);
                    orderRepository.save(order);
                });

        return CompletableFuture.completedFuture(orderRepository.findByOrderId(orderId)
                                                            .orElseThrow(() ->new NullPointerException()));

    }
}


