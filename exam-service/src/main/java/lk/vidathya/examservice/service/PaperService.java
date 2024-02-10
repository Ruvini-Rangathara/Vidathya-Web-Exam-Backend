package lk.vidathya.examservice.service;


import lk.vidathya.examservice.dto.PaperDTO;

public interface PaperService {
    String addPaper(PaperDTO paperDTO);
    PaperDTO searchPaper(int id);
    String deletePaper(int id);
    String updatePaper(PaperDTO paperDTO);
    PaperDTO[] getAllPapers();
}
