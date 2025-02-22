package com.example.questionbankservice.dto;

import com.example.questionbankservice.enums.QuestionType;
import com.example.questionbankservice.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private String title;
    private List<Answer> answers;
    private QuestionType type;
    private String courseCode;
}
