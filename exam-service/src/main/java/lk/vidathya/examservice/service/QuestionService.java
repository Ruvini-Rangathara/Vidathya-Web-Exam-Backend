package lk.vidathya.examservice.service;

import lk.vidathya.examservice.dto.QuestionDTO;

public interface QuestionService {
    String addQuestion(QuestionDTO questionDTO);
    String updateQuestion(QuestionDTO questionDTO);
    String deleteQuestion(int id);
    QuestionDTO searchQuestion(int id);
    QuestionDTO[] getAllQuestions();
}
