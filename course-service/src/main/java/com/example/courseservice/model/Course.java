package com.example.courseservice.model;

import com.example.courseservice.enums.CourseType;
import com.example.courseservice.other.CLOListConverter;
import com.example.courseservice.other.ChapterListConverter;
import com.example.sharedlibrary.model.StatusModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 150, nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int credit;
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseType type;
    @Convert(converter = ChapterListConverter.class)
    @Column(length = 500, nullable = false)
    private List<Chapter> chapters;
    @Convert(converter = CLOListConverter.class)
    @Column(length = 500, nullable = false)
    private List<CLO> clos;
    @Column(length = 10, nullable = false)
    private String majorCode;
}
