package com.example.multiple_choice_exam.dto.requests.users;

import com.example.multiple_choice_exam.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String code;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private String phone;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean deleted;

    public String getDeleted() {
        return deleted ? "Đã xóa" : "Đang hoạt động";
    }
}
