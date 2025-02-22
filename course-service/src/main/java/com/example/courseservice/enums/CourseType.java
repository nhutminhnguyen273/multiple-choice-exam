package com.example.courseservice.enums;

import com.example.sharedlibrary.exception.custom.NotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum CourseType {
    THEORY("Lý thuyết"),
    PRACTICE("Thực thành");
    private final String label;

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static CourseType fromLabel(String label) {
        return Arrays.stream(CourseType.values())
                .filter(c -> c.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException("Không tìm thấy khóa học")
                );
    }
}
