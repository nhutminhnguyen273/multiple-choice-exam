package com.example.userservice.dto.auth_dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotNull(message = "Mã không được để trống")
    @Size(min = 10, max = 10, message = "Mã không được để trống")
    private String code;
    @NotNull(message = "Mật khẩu không được trống")
    private String password;
}