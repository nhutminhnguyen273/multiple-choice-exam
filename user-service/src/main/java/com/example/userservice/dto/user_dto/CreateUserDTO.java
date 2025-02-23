package com.example.userservice.dto.user_dto;

import com.example.userservice.enums.Gender;
import com.example.userservice.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotNull(message = "Mã không được để trống")
    @Size(min = 10, max = 10, message = "Mã phải đủ 10 ký tự")
    private String code;
    @NotNull(message = "Họ và tên đệm không được để trống")
    @Pattern(regexp = "^[p{L} ]+$", message = "Họ và tên đệm chỉ chứa ký tự chữ và khoảng trắng")
    private String firstname;
    @NotNull(message = "Tên không được để trống")
    @Pattern(regexp = "^[p{L} ]+$", message = "Tên chỉ chứa ký tự chữ và khoảng trắng")
    private String lastname;
    @NotNull(message = "Ngày sinh không được trống")
    private LocalDate dateOfBirth;
    @NotNull(message = "Giới tính không được trống")
    private Gender gender;
    @NotNull(message = "Email không được trống")
    @Email(message = "Địa chỉ email không chính xác")
    private String email;
    @NotNull(message = "Số điện thoại không được trống")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải đủ 10 số")
    private String phone;
    @NotNull(message = "Mã ngành không được trống")
    @Size(min = 10, max = 10, message = "Mã ngành phải đủ 10 ký tự")
    private String majorCode;
    @NotNull(message = "Vai trò không được trống")
    private Role role;
}
