package com.example.multiple_choice_exam.exceptions.custom;

public class AlreadyExistedException extends RuntimeException {
    public AlreadyExistedException(String message) {
        super(message);
    }
}
