package ru.bevz.vit.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bevz.vit.domain.Application;
import ru.bevz.vit.domain.Event;
import ru.bevz.vit.service.ApplicationService;
import ru.bevz.vit.service.EventService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final ApplicationService appServ;

    private final EventService eventService;

    public EventController(ApplicationService appServ, EventService eventService) {
        this.appServ = appServ;
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Object> createEvents(
            @RequestBody @Valid Set<EventDto> events,
            BindingResult bindingResult) {

        for (EventDto event : events) {
            if (event.getApplicationId() == 0 || !appServ.existsAppById(event.getApplicationId())) {
                bindingResult.reject("invalid");
            }
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        events.forEach(e -> eventService.addEvent(new Event(
                e.getName(),
                e.getDescription(),
                new Application(e.getApplicationId())
        )));
        return ResponseEntity.accepted().build();
    }

}
