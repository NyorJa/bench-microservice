package com.bench.practice.order.controller;

import com.bench.practice.order.client.InventoryClient;
import com.bench.practice.order.dto.OrderDto;
import com.bench.practice.order.model.Order;
import com.bench.practice.order.repository.OrderRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {


    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Value("${test.message:helloxxxx}")
    private String message;

    @Autowired
    public OrderController(OrderRepository orderRepository,
                           InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    @GetMapping
    public String greet() {
        return message;
    }

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto) {

        boolean allProductsInStock = orderDto.getOrderLineItemsList().stream()
                .allMatch(orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));

        if (allProductsInStock) {
            Order order = Order.builder()
                               .orderLineItems(orderDto.getOrderLineItemsList())
                               .orderNumber(UUID.randomUUID().toString())
                               .build();

            orderRepository.save(order);

            return "Order Place Successfully";
        }

        return "Order failed please try again";
    }
}
