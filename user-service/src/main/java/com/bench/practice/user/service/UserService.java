package com.bench.practice.user.service;

import com.bench.practice.user.entity.User;
import com.bench.practice.user.feign.DepartmentClient;
import com.bench.practice.user.repository.UserRepository;
import com.bench.practice.user.vo.Department;
import com.bench.practice.user.vo.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentClient departmentClient;

    public User save(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
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
