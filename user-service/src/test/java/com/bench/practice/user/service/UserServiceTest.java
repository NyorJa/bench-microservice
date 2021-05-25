package com.bench.practice.user.service;

import com.bench.practice.user.entity.User;
import com.bench.practice.user.feign.DepartmentClient;
import com.bench.practice.user.vo.Department;
import com.bench.practice.user.vo.ResponseTemplateVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @MockBean
    private DepartmentClient departmentClient;

    @Autowired
    private UserService userService;

    @Test
    void testSave() {
        User user = User.builder()
                        .firstName("tayko")
                        .lastName("katulpo")
                        .departmentId(1L)
                        .build();

        User userSaved = userService.save(user);

        assertEquals(3L, userSaved.getId());
    }

    @Test
    void testGetUserWithDepartment() {

        Department department = Department.builder()
                                          .address("add")
                                          .id(1L)
                                          .code("hr")
                                          .name("hr")
                                          .build();

        when(departmentClient.findById(anyLong())).thenReturn(ResponseEntity.ok(department));

        ResponseTemplateVO actual = userService.getUserWithDepartment(2L);

        assertEquals(department, actual.getDepartment());
    }
}
