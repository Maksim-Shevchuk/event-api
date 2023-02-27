package by.vsu.eventapi.service;

import by.vsu.eventapi.exception.EventNotFoundException;
import by.vsu.eventapi.model.Event;
import by.vsu.eventapi.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        log.info("creating new event: {}", event);
        eventRepository.save(event);
        return event;
    }

    @Override
    public Event update(Event event) {
        log.info("updating existing event: {}", event);
        eventRepository.update(event);
        return event;
    }

    @Override
    public Event findById(Long id) throws EventNotFoundException {
        log.info("trying to find event with id := {}", id);
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            log.error("failed to find event with id := {}", id);
            EventNotFoundException.call(id);
        }
        return event.get();
    }


    @Override
    public List<Event> findAll() {
        log.info("getting all events");
        return eventRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        log.info("trying to delete event with id := {}", id);
        eventRepository.deleteById(id);
    }
}
