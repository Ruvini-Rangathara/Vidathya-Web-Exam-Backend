package lk.vidathya.examservice.service.impl;

import lk.vidathya.examservice.dao.MyExamDAO;
import lk.vidathya.examservice.dto.MyExamDTO;
import lk.vidathya.examservice.entity.MyExam;
import lk.vidathya.examservice.service.MyExamService;
import lk.vidathya.examservice.util.Responses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyExamServiceImpl implements MyExamService {

    private final MyExamDAO myExamDAO;
    private final ModelMapper modelMapper;

    @Override
    public String addMyExam(MyExamDTO myExamDTO) {
        long count = myExamDAO.count();
        myExamDTO.setId(count + 1);
        try {
            myExamDAO.save(modelMapper.map(myExamDTO, MyExam.class));
            return Responses.RSP_SUCCESS;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Responses.RSP_ERROR;
        }
    }

    @Override
    public MyExamDTO searchMyExam(int paperId, String nic) {
        try {
            if (myExamDAO.existsByPaperIdAndNic(paperId, nic)) {
                return modelMapper.map(myExamDAO.findByPaperIdAndNic(paperId, nic), MyExamDTO.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MyExamDTO[] getMyAllExams(String nic) {
        try {
            return modelMapper.map(myExamDAO.findAllByNic(nic), MyExamDTO[].class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean existsByPaperIdAndNic(int paperId, String nic) {
        try {
            return myExamDAO.existsByPaperIdAndNic(paperId, nic);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long countPaperByNic(String nic) {
        try {
            return myExamDAO.countPaperByNic(nic);
        } catch (Exception e) {
            return 0;
        }
    }


}
