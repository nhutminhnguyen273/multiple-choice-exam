package com.example.courseservice.dto;

import com.example.courseservice.enums.CourseType;
import com.example.courseservice.model.CLO;
import com.example.courseservice.model.Chapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private String name;
    private int credit;
    private CourseType type;
    private List<Chapter> chapters;
    private List<CLO> clos;
    private String majorCode;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean deleted;

    public String getDeleted() {
        return deleted ? "Đã xóa" : "Đang sử dụng";
    }
}
