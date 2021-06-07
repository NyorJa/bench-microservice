package com.bench.practice.product.controller;

import com.bench.practice.product.model.Product;
import com.bench.practice.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    private static final String PATH = "/api/v1/product";

    @DisplayName("Get product and return 200")
    @Test
    void testGet() throws Exception {

        Product product = Product.builder()
                                 .id(UUID.randomUUID().toString())
                                 .description("desc-1")
                                 .name("viagra")
                                 .price(BigDecimal.valueOf(120))
                                 .build();

        when(productRepository.findAll()).thenReturn(List.of(product));

        MvcResult mvcResult = mockMvc.perform(get(PATH))
                                     .andDo(print())
                                     .andExpect(status().isOk())
                                     .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertThat(responseBody)
                .isEqualToIgnoringWhitespace(new ObjectMapper().writeValueAsString(List.of(product)));
    }

    @DisplayName("Post product and created")
    @Test
    void testCreate() throws Exception {
        Product product = Product.builder()
                                 .id(UUID.randomUUID().toString())
                                 .description("desc-1")
                                 .name("viagra")
                                 .price(BigDecimal.valueOf(120))
                                 .build();


        when(productRepository.save(any(Product.class))).thenReturn(product);


        mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(new ObjectMapper().writeValueAsString(product)))
               .andDo(print())
               .andExpect(status().isCreated());
    }
}
