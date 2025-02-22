package com.example.questionbankservice.enums;

import com.example.sharedlibrary.exception.custom.NotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum QuestionType {
    YES_OR_NO("Yes/No Question"),
    SINGLE_CHOICE("Single-Choice Question"),
    MULTIPLE_CHOICE("Multiple-Choice Question");
    private final String label;

    @JsonValue
    public String getLabel() {
        return this.label;
    }

    @JsonCreator
    public static QuestionType fromLabel(String label) {
        return Arrays.stream(QuestionType.values())
                .filter(q -> q.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException("Question not found")
                );
    }
}
