package lk.vidathya.examservice.service;

import lk.vidathya.examservice.dto.MyExamDTO;

public interface MyExamService {
    String addMyExam(MyExamDTO myExamDTO);
    MyExamDTO searchMyExam(int paperId, String nic);
    MyExamDTO[] getMyAllExams(String nic);
    boolean existsByPaperIdAndNic(int paperId, String nic);
    long countPaperByNic(String nic);

}
