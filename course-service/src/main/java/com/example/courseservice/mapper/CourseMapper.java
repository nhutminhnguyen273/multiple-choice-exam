package com.example.courseservice.mapper;

import com.example.courseservice.dto.CourseDTO;
import com.example.courseservice.dto.CreateCourseDTO;
import com.example.courseservice.dto.UpdateCourseDTO;
import com.example.courseservice.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    // Chuyển DTO sang Entity
    Course toCreateCourseEntity(CreateCourseDTO input);
    Course toUpdateCourseEntity(@MappingTarget Course course, UpdateCourseDTO input);

    // Chuyển Entity sang DTO
    CourseDTO toCourseDTO(Course course);
}
