package ru.bevz.vit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bevz.vit.domain.Event;
import ru.bevz.vit.domain.User;
import ru.bevz.vit.service.ApplicationService;
import ru.bevz.vit.service.EventService;

@Controller
public class MainController {

    private final EventService eventServ;

    private final ApplicationService appServ;

    public MainController(EventService applicationService, ApplicationService appServ) {
        this.eventServ = applicationService;
        this.appServ = appServ;
    }

    @GetMapping("/")
    public String main(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        if (user != null) {
            model.addAttribute("countApp", appServ.getCountAppsByUser(user));
        }
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

        eventServ.addEvent(event);

        return "redirect:/event";
    }

}
