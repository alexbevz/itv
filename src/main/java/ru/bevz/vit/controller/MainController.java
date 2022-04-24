package ru.bevz.vit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bevz.vit.domain.Event;
import ru.bevz.vit.service.EventService;

@Controller
public class MainController {

    private final EventService eventService;

    public MainController(EventService applicationService) {
        this.eventService = applicationService;
    }

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/event")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getEventView() {
        return "event";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/event")
    public String addEvent(@ModelAttribute("event") Event event) {

        eventService.addEvent(event);

        return "redirect:/event";
    }

}
