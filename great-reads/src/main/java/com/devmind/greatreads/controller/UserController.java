package com.devmind.greatreads.controller;

import com.devmind.greatreads.dto.LoginRequestDTO;
import com.devmind.greatreads.dto.UserDTO;
import com.devmind.greatreads.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO registerRequestDTO) {
        return userService.register(registerRequestDTO);
    }

    @PostMapping("/update/{userId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Reader', 'Author')")
    public ResponseEntity<?> update(@PathVariable(name = "userId") Long userId,
                                    @RequestBody LoginRequestDTO updateRequestDTO) {
        return userService.update(userId, updateRequestDTO);
    }
}
