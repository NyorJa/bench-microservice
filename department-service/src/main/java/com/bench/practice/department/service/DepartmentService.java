package com.bench.practice.department.service;

import com.bench.practice.department.entity.Department;
import com.bench.practice.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department save(Department department) {
        log.info("Inside department service...");
        return departmentRepository.save(department);
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department id: " + id + " not existing"));
    }
}
