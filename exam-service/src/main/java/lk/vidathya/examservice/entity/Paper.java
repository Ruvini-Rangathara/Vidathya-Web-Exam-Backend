package lk.vidathya.examservice.entity;

import lk.vidathya.examservice.dto.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "paper")
public class Paper {
    private long id;
    private String title;
    private String description;
    private String subject;
    private int time;
    private String nic;

    private QuestionDTO[] questions;
}
