package com.example.userservice.enums;

import com.example.sharedlibrary.exception.custom.NotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Gender {
    MALE("Nam"),
    FEMALE("Nữ");
    private final String label;

    @JsonValue
    public String getLabel() {
        return this.label;
    }

    @JsonCreator
    public static Gender fromGender(String label) {
        return Arrays.stream(Gender.values())
                .filter(g -> g.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException("Không tìm thấy giớ tính")
                );
    }
}
