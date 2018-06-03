package com.example.microservicepurchase.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {

    @Id
    private String orderId;
    private String orderType;
    private String totalPrice;
    private List<Product> products;
    private String userId;
    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime modiDate;


    public Order(){}

}
