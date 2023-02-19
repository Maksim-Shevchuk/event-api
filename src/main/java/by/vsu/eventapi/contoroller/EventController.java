package by.vsu.eventapi.contoroller;

import by.vsu.eventapi.model.Event;
import by.vsu.eventapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Event findEventById(@PathVariable long id) {
        return eventService.findById(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Event> findALl() {
        return eventService.findAll();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Event event) {
        eventService.save(event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable long id) {
        eventService.deleteById(id);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody Event event) {
        eventService.update(event);
    }
}
