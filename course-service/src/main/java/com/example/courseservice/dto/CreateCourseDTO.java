package com.example.courseservice.dto;

import com.example.courseservice.enums.CourseType;
import com.example.courseservice.model.CLO;
import com.example.courseservice.model.Chapter;
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
public class CreateCourseDTO {
    @NotNull(message = "Tên môn học không được trống")
    private String name;
    @NotNull(message = "Số tín chỉ không được trống")
    private int credit;
    @NotNull(message = "Loại môn học không được trống")
    private CourseType type;
    @NotNull(message = "Bài học không được trống")
    private List<Chapter> chapters;
    @NotNull(message = "Chuẩn đầu ra môn học không được trống")
    private List<CLO> clos;
    @NotNull(message = "Mã ngành không được trống")
    @Size(min = 10, max = 10, message = "Mã ngành phải đủ 10 ký tự")
    private String majorCode;
}
