package com.example.multiple_choice_exam.models;

import com.example.multiple_choice_exam.converters.AnswerListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 250, nullable = false)
    private String title;
    @Column(nullable = false)
    @Convert(converter = AnswerListConverter.class)
    private List<Answer> answers;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @Column(nullable = false)
    private boolean deleted = false;
}
