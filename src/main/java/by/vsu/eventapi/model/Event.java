package by.vsu.eventapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.Hibernate;

import java.sql.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "events")
public class Event {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject", nullable = false)
    @NotBlank
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "organizer", nullable = false)
    @NotBlank
    private String organizer;

    @Column(name = "event_date", nullable = false)
    private Date eventDate;

    @Column(name = "event_place", nullable = false)
    @NotBlank
    private String eventPlace;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
