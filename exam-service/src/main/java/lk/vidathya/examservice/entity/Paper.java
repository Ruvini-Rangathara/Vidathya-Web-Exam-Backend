package lk.vidathya.examservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "paper")
public class Paper {
    private int id;
    private String title;
    private String description;
    private String subject;
    private String time;
    private String username;
}
