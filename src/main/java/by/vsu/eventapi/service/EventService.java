package by.vsu.eventapi.service;

import by.vsu.eventapi.model.Event;

import java.util.List;

public interface EventService {

    Event save(Event event);

    Event update(Event event);

    Event findById(long id);

    List<Event> findAll(int size, int page);

    void deleteById(long id);
}