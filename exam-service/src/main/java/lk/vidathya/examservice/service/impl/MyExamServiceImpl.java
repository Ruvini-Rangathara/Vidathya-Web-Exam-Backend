package lk.vidathya.examservice.service.impl;

import lk.vidathya.examservice.dao.MyExamDAO;
import lk.vidathya.examservice.dao.PaperDAO;
import lk.vidathya.examservice.dto.MyExamDTO;
import lk.vidathya.examservice.dto.PaperDTO;
import lk.vidathya.examservice.entity.MyExam;
import lk.vidathya.examservice.entity.Paper;
import lk.vidathya.examservice.service.MyExamService;
import lk.vidathya.examservice.service.PaperService;
import lk.vidathya.examservice.util.Responses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyExamServiceImpl implements MyExamService {

    private final MyExamDAO myExamDAO;
    private final PaperService paperService;
    private final ModelMapper modelMapper;

    @Override
    public String addMyExam(MyExamDTO myExamDTO) {
        try{
            if(myExamDAO.existsByPaperIdAndNic(myExamDTO.getPaperId(), myExamDTO.getNic())){
                return Responses.RSP_DUPLICATE;
            }else{
                MyExam save = myExamDAO.save(modelMapper.map(myExamDTO, MyExam.class));
                return Responses.RSP_SUCCESS;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage() );
            return Responses.RSP_ERROR;
        }
    }

    @Override
    public MyExamDTO searchMyExam(int paperId, String nic) {
        try{
            if(myExamDAO.existsByPaperIdAndNic(paperId, nic)){
                MyExamDTO myExamDTO = modelMapper.map(myExamDAO.findByPaperIdAndNic(paperId, nic), MyExamDTO.class);
                PaperDTO paperDTO = paperService.searchPaper(paperId);
                myExamDTO.setTitle(paperDTO.getTitle());
                myExamDTO.setTime(paperDTO.getTime());
                return myExamDTO;
            }else{
                return null;
            }
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public MyExamDTO[] getMyAllExams(String nic) {
        try{
            MyExamDTO[] map = modelMapper.map(myExamDAO.findAllByNic(nic), MyExamDTO[].class);
            for (int i=0; i<map.length; i++) {
                PaperDTO paperDTO = paperService.searchPaper(map[i].getPaperId());
                map[i].setTitle(paperDTO.getTitle());
                map[i].setTime(paperDTO.getTime());
            }

            return map;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean existsByPaperIdAndNic(int paperId, String nic) {
        try{
            return myExamDAO.existsByPaperIdAndNic(paperId, nic);
        }catch (Exception e){
            return false;
        }
    }


}
