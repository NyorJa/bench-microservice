package com.bench.practice.user.service;

import com.bench.practice.user.entity.User;
import com.bench.practice.user.feign.DepartmentClient;
import com.bench.practice.user.repository.UserRepository;
import com.bench.practice.user.vo.Department;
import com.bench.practice.user.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentClient departmentClient;

    public User save(User user) {
        log.info("Inside saving user");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("In save getting user with department");
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User id: " + userId + " not existing"));
        ResponseEntity<Department> departmentResponseEntity = departmentClient.findById(user.getDepartmentId());
        Department department = null;
        if (departmentResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            department = departmentResponseEntity.getBody();
        }

        return ResponseTemplateVO.builder()
                                 .department(department)
                                 .user(user)
                                 .build();
    }
}
