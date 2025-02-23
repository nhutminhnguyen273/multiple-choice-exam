package com.example.userservice.enums;

import com.example.sharedlibrary.exception.custom.NotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Role {
    ADMIN("Quản trị viên"),
    TEACHER("Giảng viên"),
    STUDENT("Sinh viên");
    private final String label;

    @JsonValue
    public String getLabel() {
        return this.label;
    }

    @JsonCreator
    public static Role fromLabel(String label) {
        return Arrays.stream(Role.values())
                .filter(r -> r.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException("Không tìm thấy vai trò")
                );
    }
}
