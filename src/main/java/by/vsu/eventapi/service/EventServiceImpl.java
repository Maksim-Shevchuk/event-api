package by.vsu.eventapi.service;

import by.vsu.eventapi.exception.EventNotFoundException;
import by.vsu.eventapi.model.Event;
import by.vsu.eventapi.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.update(event);
    }

    @Override
    public Event findById(long id) throws EventNotFoundException {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EventNotFoundException(String.format("There is no such event with id %d", id));
        } else {
            return event.get();
        }
    }

    @Override
    public List<Event> findAll(int size, int page) {
        return eventRepository.findAll(size, page);
    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }
}