package com.example.questionbankservice.other;

import com.example.questionbankservice.model.Answer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter
public class AnswerListConverter implements AttributeConverter<List<Answer>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List<Answer> answers) {
        try {
            return objectMapper.writeValueAsString(answers);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Error converting list of answers to Json", ex);
        }
    }

    @Override
    public List<Answer> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Answer>>() {});
        } catch (IOException ex) {
            throw new RuntimeException("Error converting Json to list of answers", ex);
        }
    }
}
