package com.example.questionbankservice.service;

import com.example.questionbankservice.dto.CreateQuestionDTO;
import com.example.questionbankservice.dto.QuestionDTO;
import com.example.questionbankservice.dto.UpdateQuestionDTO;
import com.example.questionbankservice.mapper.QuestionMapper;
import com.example.questionbankservice.model.Question;
import com.example.questionbankservice.other.ExcelHelper;
import com.example.questionbankservice.repository.QuestionRepository;
import com.example.sharedlibrary.exception.custom.NotFoundException;
import com.example.sharedlibrary.response.Response;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public Response<List<QuestionDTO>> getAllQuestions() {
        try {
            List<Question> questions = questionRepository.findAll();
            List<QuestionDTO> questionDTOList = questions
                    .stream()
                    .map(questionMapper::toQuestionDTO)
                    .toList();
            return new Response<>("Success", questionDTOList);
        } catch (Exception ex) {
            return new Response<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error: " + ex.getMessage()
            );
        }
    }

    public Response<QuestionDTO> findQuestionById(String questionId) {
        try {
            Question question = questionRepository.findById(questionId).orElseThrow(
                    () -> new NotFoundException("Question not found")
            );
            QuestionDTO questionDTO = questionMapper.toQuestionDTO(question);
            return new Response<>("Success", questionDTO);
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error: " + ex.getMessage()
            );
        }
    }

    public Response<QuestionDTO> createQuestion(CreateQuestionDTO input) {
        try {
            Question question = questionMapper.toCreateQuestionEntity(input);
            questionRepository.save(question);
            QuestionDTO questionDTO = questionMapper.toQuestionDTO(question);
            return new Response<>("Success", questionDTO);
        } catch (Exception ex) {
            return new Response<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error: " + ex.getMessage()
            );
        }
    }

    public Response<QuestionDTO> updateQuestion(String questionId, UpdateQuestionDTO input) {
        try {
            Question question = questionRepository.findById(questionId).orElseThrow(
                    () -> new NotFoundException("Question not found")
            );
            Question updatedQuestion = questionMapper.toUpdateQuestionEntity(question, input);
            QuestionDTO questionDTO = questionMapper.toQuestionDTO(updatedQuestion);
            return new Response<>("Success", questionDTO);
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    "Internal server error: " + ex.getMessage()
            );
        }
    }

    public Response<Void> deleteQuestion(String questionId) {
        try {
            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new NotFoundException("Question not found"));

            questionRepository.delete(question);
            return new Response<>(
                    200,
                    "Question deleted successfully"
            );
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    "Internal Server Error: " + ex.getMessage()
            );
        }
    }

    @Transactional
    public Response<Void> saveQuestionsFromExcel(MultipartFile file) {
        if (file.isEmpty())
            return new Response<>(
                    400,
                    "File không được trống"
            );
        if (!ExcelHelper.isExcelFile(file))
            return new Response<>(
                    400,
                    "File không đúng định dạng Excel!"
            );
        try {
            // Chuyển file Excel thành danh sách CreateQuestionDTO
            List<CreateQuestionDTO> questionDTOList =ExcelHelper.excelToQuestions(file.getInputStream());

            // Chuyển DTO thành danh sách Entity
            List<Question> questions = questionMapper.toQuestionList(questionDTOList);

            questionRepository.saveAll(questions);
            return new Response<>(
                    200,
                    "Danh sách câu hỏi đã được tải lên thành công"
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    "Lỗi khi lưu câu hỏi từ Excel: " + ex.getMessage()
            );
        }
    }
}
