package com.example.multiple_choice_exam.dto.requests.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeletedUserDTO {
    private boolean deleted = true;
}
