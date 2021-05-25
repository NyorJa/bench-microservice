package com.bench.practice.user.controller;

import com.bench.practice.user.entity.User;
import com.bench.practice.user.service.UserService;
import com.bench.practice.user.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> save(@RequestBody User user) {
        log.info("Inside save user");
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTemplateVO> getUserWithDepartment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserWithDepartment(id));
        } catch (IllegalArgumentException iex) {
            log.warn(iex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
