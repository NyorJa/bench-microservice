package com.programming.techie.controller;

import com.programming.techie.model.Inventory;
import com.programming.techie.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InventoryRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class InventoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryRepository inventoryRepository;

    private static final String PATH = "/api/inventory";

    @Test
    void testIsInStock() throws Exception {

        when(inventoryRepository.findBySkuCode(anyString())).thenReturn(Optional.of(
                Inventory.builder()
                         .stock(1)
                         .build()
                                                                                   ));

        mockMvc.perform(get(PATH + "/{skuCode}" , "code-1"))
               .andDo(print())
               .andExpect(status().isOk());
    }
}
