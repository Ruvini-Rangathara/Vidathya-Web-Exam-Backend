package lk.vidathya.examservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyExamDTO {
    private int id;
    private int paperId;
    private String nic;
    private double score;
}
