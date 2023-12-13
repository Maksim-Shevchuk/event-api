package by.vsu.eventapi.controller;

import by.vsu.eventapi.contoroller.EventController;
import by.vsu.eventapi.model.Event;
import by.vsu.eventapi.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    private final String URL = "/api/events";
    private final Event EVENT = new Event(1L,"skiing", "skiing", "A.G.", Date.valueOf("2022-01-01"), "Minsk");

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    {
        objectMapper.setDateFormat(DATE_FORMAT);
    }

    @Test
    public void getAllEventsTest() throws Exception {
        when(eventService.findAll(anyInt(), anyInt()))
                .thenReturn(Arrays.asList(
                        EVENT,
                        new Event(2L, "meetup","meeting", "VSU", Date.valueOf("2023-12-30"), "Vitebsk")
                ));
        mockMvc.perform(get(URL + "/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)));
    }

    @Test
    public void getEventByIdTest() throws Exception {
        long id = EVENT.getId();
        when(eventService.findById(id))
                .thenReturn(EVENT);

        mockMvc.perform(get(String.format(URL + "/%d", id)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(content().json(objectMapper.writeValueAsString(EVENT)));
    }

    @Test
    public void saveEventTest() throws Exception {
        when(eventService.save(any(Event.class)))
                .thenReturn(EVENT);

        mockMvc.perform(post(URL + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(EVENT)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(EVENT)));

    }
}