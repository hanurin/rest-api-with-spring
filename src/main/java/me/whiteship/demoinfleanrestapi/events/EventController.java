package me.whiteship.demoinfleanrestapi.events;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventController(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDto eventDto) {
//        URI createdri = linkTo(methodOn(EventController.class).createEvent(null)).slash("id").toUri();
          Event event  = modelMapper.map(eventDto, Event.class);
          Event newEvent = this.eventRepository.save(event);
          URI createdri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
          event.setId(10);
        return ResponseEntity.created(createdri).body(event);
    }
}
