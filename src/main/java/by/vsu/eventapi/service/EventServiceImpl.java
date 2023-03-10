package by.vsu.eventapi.service;

import by.vsu.eventapi.exception.EventNotFoundException;
import by.vsu.eventapi.model.Event;
import by.vsu.eventapi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        eventRepository.save(event);
        return event;
    }

    @Override
    public Event update(Event event, long id) {
        eventRepository.update(event, id);
        return event;
    }

    @Override
    public Event findById(long id) throws EventNotFoundException {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            EventNotFoundException.call(id);
        }
        return event.get();
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
