package com.example.microservicepurchase.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {

    private String productId;
    private int quntity;
    private String colorId;
    private String size;
}
