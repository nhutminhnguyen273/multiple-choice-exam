package com.example.questionbankservice.controller;

import com.example.questionbankservice.dto.CreateQuestionDTO;
import com.example.questionbankservice.dto.QuestionDTO;
import com.example.questionbankservice.dto.UpdateQuestionDTO;
import com.example.questionbankservice.service.QuestionService;
import com.example.sharedlibrary.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/")
    public Response<List<QuestionDTO>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{questionId}")
    public Response<QuestionDTO> findQuestionById(@PathVariable("questionId") String questionId) {
        return questionService.findQuestionById(questionId);
    }

    @PostMapping("/")
    public Response<QuestionDTO> createQuestion(@RequestBody CreateQuestionDTO input) {
        return questionService.createQuestion(input);
    }

    @PutMapping("/{questionId}")
    public Response<QuestionDTO> updateQuestion(
            @PathVariable("questionId") String questionId, @RequestBody UpdateQuestionDTO input) {
        return questionService.updateQuestion(questionId, input);
    }

    @DeleteMapping("/{questionId}")
    public Response<Void> deleteQuestion(@PathVariable("questionId") String questionId) {
        return questionService.deleteQuestion(questionId);
    }

    @PostMapping("/import")
    public Response<Void> importQuestions(@RequestParam("file")MultipartFile file) {
        return questionService.saveQuestionsFromExcel(file);
    }
}
