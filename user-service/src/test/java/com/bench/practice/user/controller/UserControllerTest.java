package com.bench.practice.user.controller;

import com.bench.practice.user.entity.User;
import com.bench.practice.user.service.UserService;
import com.bench.practice.user.vo.ResponseTemplateVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    private static final String ROOT_PATH = "/api/v1/user/";

    private static User user;

    @BeforeAll
    static void setUp() {
        user = User.builder()
                   .id(1L)
                   .departmentId(1L)
                   .email("a@a.com")
                   .firstName("a")
                   .lastName("b")
                   .build();
    }

    @AfterAll
    static void tearDown() {
        user = null;
    }

    @Test
    void testSave() throws Exception {

        String payload = mapper.writeValueAsString(user);

        when(userService.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post(ROOT_PATH + "/")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andDo(print())
               .andExpect(status().isCreated());
    }

    @Test
    void testGetUserWithDepartment() throws Exception {

        when(userService.getUserWithDepartment(anyLong())).thenReturn(ResponseTemplateVO.builder()
                                                                                        .user(user)
                                                                                        .build());

        mockMvc.perform(get(ROOT_PATH + "/1"))
               .andDo(print())
               .andExpect(status().isOk());
    }
}
