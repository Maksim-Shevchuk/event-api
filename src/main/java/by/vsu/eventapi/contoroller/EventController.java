package by.vsu.eventapi.contoroller;

import by.vsu.eventapi.model.Event;
import by.vsu.eventapi.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Event", description = "The event api")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Fetch an event by it's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched an event from db",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "There is no such event",
                    content = @Content)
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event findEventById(@PathVariable @Valid long id) {
        return eventService.findById(id);
    }

    @Operation(summary = "Fetch all events with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all events with pagination",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Event.class)))})
    })
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Event> findAll(
            @RequestParam(defaultValue = "20") @Positive int pageSize,
            @RequestParam(defaultValue = "1") @Positive int page
    ) {
        return eventService.findAll(pageSize, page);
    }

    @Operation(summary = "Create event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Created new event",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Event save(@RequestBody @Valid Event event) {
        return eventService.save(event);
    }

    @Operation(summary = "Delete event by it's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Event deleted from db",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "There is no such event",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @Valid long id) {
        eventService.deleteById(id);
    }

    @Operation(summary = "Update existing event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched and updated existing event",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "There is no such event",
                    content = @Content)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event updateEvent(@PathVariable @Valid long id, @RequestBody @Valid Event event) {
        event.setId(id);
        return eventService.update(event);
    }
}
