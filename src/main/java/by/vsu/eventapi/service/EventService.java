package by.vsu.eventapi.service;

import by.vsu.eventapi.model.Event;

import java.util.List;

public interface EventService {

    void save(Event event);

    void update(Event event);

    Event findById(Long id);

    List<Event> findAll();

    void deleteById(Long id);
}
