package com.example.userservice.controller;

import com.example.sharedlibrary.response.Response;
import com.example.userservice.dto.user_dto.CreateUserDTO;
import com.example.userservice.dto.user_dto.UpdateUserDTO;
import com.example.userservice.dto.user_dto.UserDTO;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public Response<List<UserDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Response<UserDTO> findUserById(@PathVariable("userId") String userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/")
    public Response<UserDTO> createUser(@RequestBody CreateUserDTO input) {
        return userService.createUser(input);
    }

    @PutMapping("/{userId}")
    public Response<UserDTO> updateUser(
            @PathVariable("userId") String userId, @RequestBody UpdateUserDTO input
    ) {
        return userService.updateUser(userId, input);
    }

    @DeleteMapping("/{userId}")
    public Response<Void> deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUser(userId);
    }
}
