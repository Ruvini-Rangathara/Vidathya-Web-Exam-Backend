package lk.vidathya.examservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "my_exam")
public class MyExam {
    private int id;
    private int paperId;
    private String nic;
    private double score;
    private String subject;
    private int time;
}

