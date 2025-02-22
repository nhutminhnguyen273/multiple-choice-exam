package com.example.questionbankservice.model;

import com.example.questionbankservice.other.AnswerListConverter;
import com.example.questionbankservice.enums.QuestionType;
import com.example.sharedlibrary.model.StatusModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 250, nullable = false)
    private String title;
    @Convert(converter = AnswerListConverter.class)
    @Column(length = 500, nullable = false)
    private List<Answer> answers;
    @Column(length = 150, nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    @Column(length = 10, nullable = false)
    private String courseCode;
}
