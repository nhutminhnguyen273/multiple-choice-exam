package com.example.questionbankservice.other;

import com.example.questionbankservice.dto.CreateQuestionDTO;
import com.example.questionbankservice.enums.QuestionType;
import com.example.questionbankservice.model.Answer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static  String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    // Kiểm tra file có đúng định dạng Excel không
    public static boolean isExcelFile(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    // Hàm đọc danh sách câu hỏi từ file Excel
    public static List<CreateQuestionDTO> excelToQuestions(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<CreateQuestionDTO> questions = new ArrayList<>();

            // Bỏ qua dòng tiêu đề
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                CreateQuestionDTO question = new CreateQuestionDTO();

                question.setTitle(row.getCell(0).getStringCellValue());
                question.setType(QuestionType.valueOf(row.getCell(1).getStringCellValue()));
                question.setCourseCode(row.getCell(2).getStringCellValue());

                List<Answer> answers = new ArrayList<>();
                String[] answerTexts = row.getCell(3).getStringCellValue().split(";");
                for (String ans : answerTexts) {
                    String[] parts = ans.split(",");
                    if (parts.length == 2) {
                        String title = parts[0].trim();
                        boolean correct = Boolean.parseBoolean(parts[1].trim());
                        answers.add(new Answer(title, correct));
                    }
                }

                question.setAnswers(answers);

                questions.add(question);
            }
            return questions;
        } catch (Exception ex) {
            throw new RuntimeException("Lỗi đọc file Excel: " + ex.getMessage());
        }
    }
}
