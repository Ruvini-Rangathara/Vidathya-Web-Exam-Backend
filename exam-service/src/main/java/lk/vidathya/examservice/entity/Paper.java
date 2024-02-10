package lk.vidathya.examservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Paper {
    @Id
    private int id;
    private String title;
    private String description;
    private String subject;
    private String time;
    private String username;
}
