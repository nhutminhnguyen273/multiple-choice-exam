package com.example.questionbankservice.mapper;

import com.example.questionbankservice.dto.CreateQuestionDTO;
import com.example.questionbankservice.dto.QuestionDTO;
import com.example.questionbankservice.dto.UpdateQuestionDTO;
import com.example.questionbankservice.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    // Convert DTO to Entity
    Question toCreateQuestionEntity(CreateQuestionDTO input);
    Question toUpdateQuestionEntity(@MappingTarget Question question, UpdateQuestionDTO input);
    List<Question> toQuestionList(List<CreateQuestionDTO> list);

    // Convert Entity to DTO
    QuestionDTO toQuestionDTO(Question question);
}
