package com.bench.practice.inventory.service.controller;

import com.bench.practice.inventory.service.model.Inventory;
import com.bench.practice.inventory.service.repository.InventoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    private static final String PATH = "/api/v1/inventory";
    @MockBean
    private InventoryRepository inventoryRepository;
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Check stock is existing")
    @Test
    void testCheckStock() throws Exception {
        when(inventoryRepository.findBySkuCode(anyString())).thenReturn(Optional.of(Inventory.builder()
                                                                                             .id(1L)
                                                                                             .skuCode("code-xyz")
                                                                                             .stock(100)
                                                                                             .build()));


        mockMvc.perform(get(PATH + "/{skuCode}", "code-xyz"))
               .andDo(print())
               .andExpect(status().isOk());
    }


    @DisplayName("Check stock is not existing and throw runtime exception")
    @Test
    void testCheckStockThrowException() throws Exception {
        when(inventoryRepository.findBySkuCode(anyString())).thenReturn(Optional.empty());


        mockMvc.perform(get(PATH + "/{skuCode}", "code-xyz"))
               .andDo(print())
               .andExpect(status().isNotFound());
    }
}
