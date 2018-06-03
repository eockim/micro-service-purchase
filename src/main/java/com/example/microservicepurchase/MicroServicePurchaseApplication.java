package com.example.microservicepurchase;

import com.example.microservicepurchase.entity.Order;
import com.example.microservicepurchase.entity.Product;
import com.example.microservicepurchase.repository.OrderRepository;
import com.example.microservicepurchase.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class MicroServicePurchaseApplication {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(MicroServicePurchaseApplication.class, args);
    }


    @GetMapping("/order")
    public Flux<Order> getOrder(){

       return Flux.fromIterable(orderService.getOrders());
    }

    @GetMapping("/order/{orderId}")
    public Mono<Order> getOrderById(@PathVariable String orderId){
        return Mono.fromCompletionStage(orderService.getOrderByOrderId(orderId));
    }

    @PostMapping("/order")
    public Mono<Order> createOrder(@RequestBody Order order){
        return Mono.fromCompletionStage(orderService.createOrder(order));
    }

    @PostMapping("/order/createWithArray")
    public List<Order> createArrayOrder(){
        return new ArrayList<Order>();
    }

    @DeleteMapping("/order/{orderId}")
    public Flux<Order> delteOrder(@PathVariable String orderId){
        return orderService.deleteOrder(orderId);
    }

    @PutMapping("/order/{orderId}")
    public Mono<Order> updateOrder(@PathVariable String orderId, @RequestBody Order order){
        return Mono.fromCompletionStage(orderService.updateOrder(orderId, order));
    }


}
