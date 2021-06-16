package com.programming.techie.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.techie.orderservice.client.InventoryClient;
import com.programming.techie.orderservice.dto.OrderDto;
import com.programming.techie.orderservice.model.Order;
import com.programming.techie.orderservice.model.OrderLineItems;
import com.programming.techie.orderservice.repository.OrderRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
    private static final String PATH = "/api/order";
    @MockBean
    private Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    @MockBean
    private InventoryClient inventoryClient;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private StreamBridge streamBridge;
    @MockBean
    private ExecutorService traceableExecutorService;

    @Test
    void testGetOrders() throws Exception {
        when(orderRepository.findAll()).thenReturn(Lists.newArrayList());

        mockMvc.perform(get(PATH))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    void testPlaceOrder() throws Exception {
        Resilience4JCircuitBreaker circuitBreaker = Mockito.mock(Resilience4JCircuitBreaker.class);
        when(circuitBreakerFactory.create(anyString())).thenReturn(circuitBreaker);
        when(circuitBreaker.run(any(), any())).thenReturn(Boolean.TRUE);
        when(inventoryClient.checkStock(anyString())).thenReturn(Boolean.TRUE);
        when(orderRepository.save(any(Order.class))).thenReturn(Order.builder()
                                                                     .id(1L)
                                                                     .build());


        mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(new ObjectMapper()
                                                 .writeValueAsString(OrderDto.builder()
                                                                             .orderLineItemsList(Lists.newArrayList(
                                                                                     OrderLineItems.builder()
                                                                                                   .id(1L)
                                                                                                   .skuCode("code-1")
                                                                                                   .build()
                                                                                                                   ))
                                                                             .build())))
               .andDo(print())
               .andExpect(status().isOk());
    }
}
