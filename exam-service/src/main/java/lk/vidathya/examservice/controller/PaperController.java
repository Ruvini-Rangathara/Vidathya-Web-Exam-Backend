package lk.vidathya.examservice.controller;

import lk.vidathya.examservice.dto.PaperDTO;
import lk.vidathya.examservice.dto.ResponseDTO;
import lk.vidathya.examservice.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/api/v1/paper")
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/add")
    public ResponseDTO addPaper(@RequestBody PaperDTO paperDTO) {
        String result = paperService.addPaper(paperDTO);

        if (result.equals("00")) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Paper added successfully");
            responseDTO.setContent(paperDTO);
        } else if (result.equals("03")) {
            responseDTO.setCode("03");
            responseDTO.setMessage("Paper already exists");
            responseDTO.setContent(null);
        } else {
            responseDTO.setCode("02");
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @GetMapping(value = "/get/{id}")
    public ResponseDTO searchPaper(@PathVariable int id) {
        PaperDTO paperDTO = paperService.searchPaper(id);
        if (paperDTO != null) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Paper found");
            responseDTO.setContent(paperDTO);
        } else {
            responseDTO.setCode("04");
            responseDTO.setMessage("Paper not found");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDTO deletePaper(@PathVariable int id) {
        String result = paperService.deletePaper(id);
        if (result.equals("00")) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Paper deleted successfully");
            responseDTO.setContent(null);
        } else {
            responseDTO.setCode("04");
            responseDTO.setMessage("Paper not found");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @PutMapping(value = "/update")
    public ResponseDTO updatePaper(@RequestBody PaperDTO paperDTO) {
        String result = paperService.updatePaper(paperDTO);
        if (result.equals("00")) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Paper updated successfully");
            responseDTO.setContent(paperDTO);
        } else {
            responseDTO.setCode("04");
            responseDTO.setMessage("Paper not found");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @GetMapping(value = "/getAll")
    public ResponseDTO getAllPapers() {
        PaperDTO[] paperDTOs = paperService.getAllPapers();
        if (paperDTOs.length > 0) {
            responseDTO.setCode("00");
            responseDTO.setMessage("Papers found");
            responseDTO.setContent(paperDTOs);
        } else {
            responseDTO.setCode("02");
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
        }
        return responseDTO;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/count")
    public ResponseDTO countPapers() {
        long count = paperService.countPapers();
        responseDTO.setCode("00");
        responseDTO.setMessage("Papers count");
        responseDTO.setContent(count);
        return responseDTO;
    }



}
