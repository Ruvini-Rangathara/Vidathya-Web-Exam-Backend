package lk.vidathya.examservice.controller;

import lk.vidathya.examservice.dto.MyExamDTO;
import lk.vidathya.examservice.dto.ResponseDTO;
import lk.vidathya.examservice.service.MyExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/api/v1/myexam")
@RequiredArgsConstructor
public class MyExamController {
    private final MyExamService myExamService;

    @Autowired
    private ResponseDTO responseDTO;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "/add")
    public ResponseDTO addMyExam(@RequestBody MyExamDTO myExamDTO) {
        String result = myExamService.addMyExam(myExamDTO);

        System.out.println("result : "+result);

        if (result.equals("00")) {
            responseDTO.setCode("00");
            responseDTO.setMessage("My Exam added successfully");
            responseDTO.setContent(myExamDTO);
        } else if (result.equals("03")) {
            responseDTO.setCode("03");
            responseDTO.setMessage("My Exam already exists");
            responseDTO.setContent(null);
        } else {
            responseDTO.setCode("02");
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping(value = "/get/{paperId}/{nic}")
    public ResponseDTO searchPaper(@PathVariable int paperId, @PathVariable String nic) {
        MyExamDTO myExamDTO = myExamService.searchMyExam(paperId, nic);
        if (myExamDTO != null) {
            responseDTO.setCode("00");
            responseDTO.setMessage("My Exam found");
            responseDTO.setContent(myExamDTO);
        } else {
            responseDTO.setCode("04");
            responseDTO.setMessage("My Exam not found");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/getAll/{nic}")
    public ResponseDTO getAllMyExams(@PathVariable String nic) {
        MyExamDTO[] myExamDTOS = myExamService.getMyAllExams(nic);
        if (myExamDTOS.length > 0) {
            responseDTO.setCode("00");
            responseDTO.setMessage("My Exam found");
            responseDTO.setContent(myExamDTOS);
        } else {
            responseDTO.setCode("02");
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/count/{nic}")
    public ResponseDTO countMyExams(@PathVariable String nic) {
        long count = myExamService.countPaperByNic(nic);
        if (count > 0) {
            responseDTO.setCode("00");
            responseDTO.setMessage("My Exam found");
            responseDTO.setContent(count);
        } else {
            responseDTO.setCode("02");
            responseDTO.setMessage("Error");
            responseDTO.setContent(0);
        }
        return responseDTO;
    }


}
