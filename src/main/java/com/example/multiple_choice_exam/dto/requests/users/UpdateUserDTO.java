package com.example.multiple_choice_exam.dto.requests.users;

import com.example.multiple_choice_exam.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    @NotNull
    @Pattern(regexp = "^[p{L} ]+$", message = "Họ và tên đệm chỉ chứa ký tự chữ và khoảng trắng")
    private String firstname;
    @NotNull
    @Pattern(regexp = "^[p{L} ]+$", message = "Tên chỉ chứa ký tự chữ và khoảng trắng")
    private String lastname;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private Gender gender;
    @NotNull
    @Email(message = "Địa chỉ email không chính xác")
    private String email;
    @NotNull
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải đủ 10 số")
    private String phone;
}
