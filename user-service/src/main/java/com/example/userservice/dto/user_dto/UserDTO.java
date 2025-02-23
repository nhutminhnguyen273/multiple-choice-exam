package com.example.userservice.dto.user_dto;

import com.example.userservice.enums.Gender;
import com.example.userservice.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String code;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private String phone;
    private String majorCode;
    private Role role;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean deleted;

    public String getDeleted() {
        return deleted ? "Đã xóa" : "Đang sử dụng";
    }
}
