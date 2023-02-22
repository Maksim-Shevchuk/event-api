package by.vsu.eventapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    //    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "organizer", nullable = false)
    @NotBlank
    private String organizer;

    @Column(name = "time_of_event", nullable = false)
    private Date timeOfEvent;

    @Column(name = "place_of_event", nullable = false)
    @NotBlank
    private String placeOfEvent;
}
