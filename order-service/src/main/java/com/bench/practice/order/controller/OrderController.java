package com.bench.practice.order.controller;

import com.bench.practice.order.client.InventoryClient;
import com.bench.practice.order.dto.OrderDto;
import com.bench.practice.order.model.Order;
import com.bench.practice.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {


    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final Resilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory;

    @Value("${test.message:helloxxxx}")
    private String message;

    @Autowired
    public OrderController(OrderRepository orderRepository,
                           InventoryClient inventoryClient,
                           Resilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.resilience4JCircuitBreakerFactory = resilience4JCircuitBreakerFactory;
    }

    @GetMapping
    public String greet() {
        return message;
    }

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto) {

        Resilience4JCircuitBreaker circuitBreaker = resilience4JCircuitBreakerFactory.create("inventory-service");
        Supplier<Boolean> booleanSupplier = () -> orderDto.getOrderLineItemsList().stream()
                              .allMatch(orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));
//        boolean allProductsInStock = orderDto.getOrderLineItemsList().stream()
//                .allMatch(orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));

        boolean productsInStock = circuitBreaker.run(booleanSupplier, throwable -> handleErrorCase());

        if (productsInStock) {
            Order order = Order.builder()
                               .orderLineItems(orderDto.getOrderLineItemsList())
                               .orderNumber(UUID.randomUUID().toString())
                               .build();

            orderRepository.save(order);

            return "Order Place Successfully";
        }

        return "Order failed please try again";
    }

    private Boolean handleErrorCase() {
        return false;
    }
}
