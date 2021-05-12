package com.bench.practice.department.controller;

import com.bench.practice.department.DepartmentServiceApplication;
import com.bench.practice.department.entity.Department;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DepartmentServiceApplication.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private static final String ROOT_PATH = "/api/v1/department/";

    @Test
    void testSave() throws Exception {

        Department department = Department.builder()
                                          .address("taguig")
                                          .code("oic")
                                          .name("oic")
                                          .build();
        String payload = mapper.writeValueAsString(department);

        mockMvc.perform(post(ROOT_PATH + "/")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andDo(print())
               .andExpect(status().isCreated());
    }

    @Test
    void testFindById() throws Exception {
        mockMvc.perform(get(ROOT_PATH + "/1"))
               .andDo(print())
               .andExpect(status().isOk());
    }
}
