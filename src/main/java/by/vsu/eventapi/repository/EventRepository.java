package by.vsu.eventapi.repository;

import by.vsu.eventapi.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    void save(Event event);

    void update(Event event);

    Optional<Event> findById(Long id);

    List<Event> findAll();

    void deleteById(Long id);
}
