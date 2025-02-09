package com.example.multiple_choice_exam.controllers;

import com.example.multiple_choice_exam.dto.requests.users.CreateUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.DeletedUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.UpdateUserDTO;
import com.example.multiple_choice_exam.dto.requests.users.UserDTO;
import com.example.multiple_choice_exam.dto.responses.ApiResponse;
import com.example.multiple_choice_exam.models.User;
import com.example.multiple_choice_exam.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        var userDTOList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable("userId") String userId) {
        var userDTO = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody CreateUserDTO input) {
        var user = userService.createUser(input);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable("userId") String userId,
                                                        @RequestBody UpdateUserDTO input) {
        var user = userService.updateUser(userId, input);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/deleted/{userId}")
    public ResponseEntity<ApiResponse<User>> deletedUser(@PathVariable("userId") String userId,
                                                         @RequestBody DeletedUserDTO deleted) {
        var deletedUser = userService.deletedUser(userId, deleted);
        return ResponseEntity.status(HttpStatus.OK).body(deletedUser);
    }
}
