package ru.bevz.vit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bevz.vit.domain.Application;
import ru.bevz.vit.domain.Event;
import ru.bevz.vit.domain.User;
import ru.bevz.vit.service.ApplicationService;
import ru.bevz.vit.service.EventService;

import javax.validation.Valid;
import java.util.regex.Pattern;

@Controller
public class MainController {

    private final EventService eventServ;

    private final ApplicationService appServ;

    private final ControllerUtils controllerUtils;

    public MainController(EventService applicationService, ApplicationService appServ, ControllerUtils controllerUtils) {
        this.eventServ = applicationService;
        this.appServ = appServ;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping("/")
    public String main(@AuthenticationPrincipal User user, Model model) {
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
    public String addEvent(@RequestParam String appId, @Valid Event event, BindingResult bindingResult, Model model) {

        if (!Pattern.matches("^[1-9]\\d*$", appId)) {
            bindingResult.rejectValue("application", "applicationError", "идентификатор должен быть положительным целым числом");
        } else {
            if (!appServ.existsAppById(Long.parseLong(appId))) {
                bindingResult.rejectValue("application", "applicationError", "данное приложение не существует");
            }
        }


        if (bindingResult.hasErrors()) {
            model.mergeAttributes(controllerUtils.getErrors(bindingResult));
            model.addAttribute("event", event);
        } else {
            Application app = new Application();
            app.setId(Long.parseLong(appId));
            event.setApplication(app);
            eventServ.addEvent(event);
            model.addAttribute("success", "событие успешно добавлено");
        }

        model.addAttribute("appId", appId);

        return "event";
    }

}
