package lk.vidathya.examservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaperDTO {
    private int id;
    private String title;
    private String description;
    private String subject;
    private String time;
    private String username;
}
