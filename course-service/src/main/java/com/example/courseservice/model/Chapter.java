package com.example.courseservice.model;

import com.example.courseservice.other.CLOListConverter;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    private String title;
    private int numberOfLessons;
    @Convert(converter = CLOListConverter.class)
    private List<CLO> cloList;
}
