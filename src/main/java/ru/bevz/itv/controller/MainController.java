package ru.bevz.itv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bevz.itv.controller.model.EventModel;
import ru.bevz.itv.dto.EventDto;
import ru.bevz.itv.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ApplicationService applicationService;

    @ModelAttribute("event")
    public EventModel getEventModel() {
        return new EventModel();
    }

    @GetMapping
    public String root() {
        return "index";
    }

    @GetMapping("login")
    public String login(Model model, HttpServletRequest request) {

        model.addAttribute(
                "errorLogin", request.isUserInRole("ROLE_USER")
        );

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return "login";
    }

    @GetMapping("event")
    public ResponseEntity<Object> addEvent(
            @RequestParam int idApplication,
            @RequestParam String nameEvent,
            @RequestParam String description
    ) {
        EventDto eventDto = new EventDto()
                .setIdApplication(idApplication)
                .setName(nameEvent)
                .setDescription(description);

        applicationService.addEvent(eventDto);

        return ResponseEntity.accepted().build();
    }

    @PostMapping("event")
    public ResponseEntity<Object> addEvent(
            @ModelAttribute("event") EventModel eventModel
    ) {

        applicationService.addEvent(
                new EventDto()
                        .setIdApplication(eventModel.getIdApplication())
                        .setName(eventModel.getName())
                        .setDescription(eventModel.getDescription())
        );

        return ResponseEntity.accepted().build();
    }
}
