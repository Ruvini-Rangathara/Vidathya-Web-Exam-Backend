package lk.vidathya.examservice.service.impl;

import lk.vidathya.examservice.dao.QuestionDAO;
import lk.vidathya.examservice.dto.QuestionDTO;
import lk.vidathya.examservice.entity.Question;
import lk.vidathya.examservice.service.QuestionService;
import lk.vidathya.examservice.util.Responses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;
    private final ModelMapper modelMapper;

    @Override
    public String addQuestion(QuestionDTO questionDTO) {
        try {
            if (questionDAO.existsById(questionDTO.getId())) {
                return Responses.RSP_DUPLICATE;
            } else {
                questionDAO.save(modelMapper.map(questionDTO, Question.class));
                return Responses.RSP_SUCCESS;
            }
        } catch (Exception e) {
            return Responses.RSP_ERROR;
        }
    }

    @Override
    public String updateQuestion(QuestionDTO questionDTO) {
        if (questionDAO.existsById(questionDTO.getId())) {
            questionDAO.save(modelMapper.map(questionDTO, Question.class));
            return Responses.RSP_SUCCESS;
        } else {
            return Responses.RSP_NOT_FOUND;
        }
    }

    @Override
    public String deleteQuestion(int id) {
        if (questionDAO.existsById(id)) {
            questionDAO.deleteById(id);
            return Responses.RSP_SUCCESS;
        } else {
            return Responses.RSP_NOT_FOUND;
        }
    }

    @Override
    public QuestionDTO searchQuestion(int id) {
        if (questionDAO.existsById(id))
            return modelMapper.map(questionDAO.findById(id), QuestionDTO.class);
        else
            return null;
    }

    @Override
    public QuestionDTO[] getAllQuestions() {
        try {
            return modelMapper.map(questionDAO.findAll(), QuestionDTO[].class);
        } catch (Exception e) {
            return null;
        }
    }
}
