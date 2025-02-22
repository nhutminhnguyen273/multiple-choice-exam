package com.example.courseservice.other;

import com.example.courseservice.model.CLO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter
public class CLOListConverter implements AttributeConverter<List<CLO>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List<CLO> clos) {
        try {
            return objectMapper.writeValueAsString(clos);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Error converting list of CLOs to Json", ex);
        }
    }

    @Override
    public List<CLO> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<CLO>>() {});
        } catch (IOException ex) {
            throw new RuntimeException("Error converting Json to list of CLOs", ex);
        }
    }
}
