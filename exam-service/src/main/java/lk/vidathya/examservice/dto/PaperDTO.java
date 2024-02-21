package lk.vidathya.examservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaperDTO {
    private long id;
    private String title;
    private String description;
    private String subject;
    private int time;
    private String nic;

    private QuestionDTO[] questions;
}
