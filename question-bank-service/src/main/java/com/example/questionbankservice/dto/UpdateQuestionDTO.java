package com.example.questionbankservice.dto;

import com.example.questionbankservice.enums.QuestionType;
import com.example.questionbankservice.model.Answer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionDTO {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Answers is required")
    private List<Answer> answers;
    @NotNull(message = "Question type is required")
    private QuestionType type;
    @NotNull(message = "Course code is required")
    @Size(min = 10, max = 10, message = "Course code must be 10 characters")
    private String courseCode;
}
