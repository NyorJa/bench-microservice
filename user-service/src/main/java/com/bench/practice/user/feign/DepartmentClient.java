package com.bench.practice.user.feign;

import com.bench.practice.user.vo.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "http://DEPARTMENT-SERVICE/api/v1/department")
public interface DepartmentClient {

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Department> findById(@PathVariable Long id);
}
