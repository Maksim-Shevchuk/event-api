package by.vsu.eventapi.model;

import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "organizer", nullable = false)
    private String organizer;

    @Column(name = "time_of_event", nullable = false)
    private Date timeOfEvent;

    @Column(name = "place_of_event", nullable = false)
    private String placeOfEvent;
}
