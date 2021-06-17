package com.programming.techie.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.techie.productservice.model.Product;
import com.programming.techie.productservice.repository.ProductRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private JwtDecoder jwtDecoder;

    private static final String PATH = "/api/product";

    @Test
    void testFindAll() throws Exception {
        when(productRepository.findAll()).thenReturn(Lists.newArrayList());

        mockMvc.perform(get(PATH))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    void testCreateProduct() throws Exception {
        mockMvc.perform(post(PATH)
                                .content(new ObjectMapper().writeValueAsString(
                                        Product.builder()
                                               .name("xialis")
                                               .description("pampautog")
                                               .build()))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andDo(print())
               .andExpect(status().isCreated());
    }
}
