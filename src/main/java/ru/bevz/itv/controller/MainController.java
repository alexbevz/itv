package ru.bevz.itv.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bevz.itv.controller.model.EventModel;
import ru.bevz.itv.dto.EventDto;
import ru.bevz.itv.dto.mapper.EventMapper;
import ru.bevz.itv.service.EventService;

@Controller
public class MainController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    public MainController(EventService applicationService, EventMapper eventMapper) {
        this.eventService = applicationService;
        this.eventMapper = eventMapper;
    }

    @ModelAttribute("event")
    public EventModel getEventModel() {
        return new EventModel();
    }

    @GetMapping
    public String main() {
        return "index";
    }

    @GetMapping("/event")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getEventView(@ModelAttribute("event") EventModel eventModel) {
        return "event";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/event")
    public String addEvent(@ModelAttribute("event") EventModel eventModel) {
        //TODO: to need to add some handler
        EventDto eventDto = eventService.addEvent(eventMapper.toEventDto(eventModel));

        return "redirect:/event";
    }
}
