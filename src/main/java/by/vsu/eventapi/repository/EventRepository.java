package by.vsu.eventapi.repository;

import by.vsu.eventapi.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    Event save(Event event);

    Event update(Event event);

    Optional<Event> findById(long id);

    List<Event> findAll(int size, int page);

    void deleteById(long id);
}