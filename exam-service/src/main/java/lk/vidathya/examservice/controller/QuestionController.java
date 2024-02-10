package lk.vidathya.examservice.controller;

import lk.vidathya.examservice.dto.QuestionDTO;
import lk.vidathya.examservice.dto.ResponseDTO;
import lk.vidathya.examservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping(value = "/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/add")
    public ResponseDTO addQuestion(@RequestBody QuestionDTO questionDTO) {
        String result = questionService.addQuestion(questionDTO);
        if (result.equals("00")) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Question added successfully");
            responseDTO.setContent(questionDTO);
        } else if (result.equals("03")) {
            responseDTO.setCode("03");
            responseDTO.setMessage("Question already exists");
            responseDTO.setContent(null);
        } else {
            responseDTO.setCode("02");
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @GetMapping(value = "/get/{id}")
    public ResponseDTO searchQuestion(@PathVariable int id) {
        QuestionDTO questionDTO = questionService.searchQuestion(id);
        if (questionDTO != null) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Question found");
            responseDTO.setContent(questionDTO);
        } else {
            responseDTO.setCode("04");
            responseDTO.setMessage("Question not found");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDTO deleteQuestion(@PathVariable int id) {
        String result = questionService.deleteQuestion(id);
        if (result.equals("00")) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Question deleted successfully");
            responseDTO.setContent(null);
        } else {
            responseDTO.setCode("04");
            responseDTO.setMessage("Question not found");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @PutMapping(value = "/update")
    public ResponseDTO updateQuestion(@RequestBody QuestionDTO questionDTO) {
        String result = questionService.updateQuestion(questionDTO);
        if (result.equals("00")) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Question updated successfully");
            responseDTO.setContent(questionDTO);
        } else {
            responseDTO.setCode("04");
            responseDTO.setMessage("Question not found");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @GetMapping(value = "/getAll")
    public ResponseDTO getAllQuestions() {
        QuestionDTO[] questionDTOs = questionService.getAllQuestions();
        if (questionDTOs != null) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Questions found");
            responseDTO.setContent(questionDTOs);
        } else {
            responseDTO.setCode("04");
            responseDTO.setMessage("Questions not found");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

}
