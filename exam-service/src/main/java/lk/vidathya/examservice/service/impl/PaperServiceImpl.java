package lk.vidathya.examservice.service.impl;

import lk.vidathya.examservice.dao.PaperDAO;
import lk.vidathya.examservice.dto.PaperDTO;
import lk.vidathya.examservice.entity.Paper;
import lk.vidathya.examservice.service.PaperService;
import lk.vidathya.examservice.util.Responses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService{

    private final PaperDAO paperDAO;
    private final ModelMapper modelMapper;

    @Override
    public String addPaper(PaperDTO paperDTO) {
        try{
            if(paperDAO.existsById(paperDTO.getId())){
                return Responses.RSP_DUPLICATE;
            }else{
                Paper save = paperDAO.save(modelMapper.map(paperDTO, Paper.class));
                return Responses.RSP_SUCCESS;
            }
        }catch (Exception e) {
            return Responses.RSP_ERROR;
        }
    }

    @Override
    public PaperDTO searchPaper(int id) {
        if (paperDAO.existsById(id))
            return modelMapper.map(paperDAO.findById(id), PaperDTO.class);
        else
            return null;
    }

    @Override
    public String deletePaper(int id) {
        if (paperDAO.existsById(id)){
            paperDAO.deleteById(id);
            return Responses.RSP_SUCCESS;
        }else{
            return Responses.RSP_NOT_FOUND;
        }
    }

    @Override
    public String updatePaper(PaperDTO paperDTO) {
        if (paperDAO.existsById(paperDTO.getId())){
            paperDAO.save(modelMapper.map(paperDTO, Paper.class));
            return Responses.RSP_SUCCESS;
        }else{
            return Responses.RSP_NOT_FOUND;
        }
    }

    @Override
    public PaperDTO[] getAllPapers() {
        try{
            return modelMapper.map(paperDAO.findAll(), PaperDTO[].class);
        }catch (Exception e){
            return null;
        }
    }
}
