package com.example.courseservice.other;

import com.example.courseservice.model.Chapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter
public class ChapterListConverter implements AttributeConverter<List<Chapter>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List<Chapter> chapters) {
        try {
            return objectMapper.writeValueAsString(chapters);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Error converting list of chapters to Json", ex);
        }
    }

    @Override
    public List<Chapter> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Chapter>>() {});
        } catch (IOException ex) {
            throw new RuntimeException("Error converting Json to list of chapters", ex);
        }
    }
}
