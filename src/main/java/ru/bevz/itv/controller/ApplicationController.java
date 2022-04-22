package ru.bevz.itv.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bevz.itv.domain.Application;
import ru.bevz.itv.domain.User;
import ru.bevz.itv.service.ApplicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
public class ApplicationController {

    private final ApplicationService appServ;

    public ApplicationController(ApplicationService appServ) {
        this.appServ = appServ;
    }

    @GetMapping("/applications")
    public String getListApplications(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("apps", appServ.getByUser(user));
        return "/user/applications";
    }

    @GetMapping("/applications/add")
    public String getFormForAddApplication(
            @AuthenticationPrincipal User user,
            Model model
    ) {

        model.addAttribute("app", appServ.preAddApplicationForUser(user));

        return "/user/applicationAdd";
    }

    @PostMapping("/applications")
    public String addApplication(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            Model model
    ) {

        if (name == null || name.isEmpty()) {
            model.addAttribute("nameError", "название приложения не может быть пустым");
            return "/user/applicationAdd";
        }

        Application app = new Application();
        app.setName(name);
        appServ.addApplicationForUser(app, user);

        return "redirect:/user/applications";
    }

    @GetMapping("/applications/{app}")
    public String detailApplication(
            @PathVariable Application app,
            @RequestParam(value = "timeFrom", required = false) String timeFromStr,
            @RequestParam(value = "timeTo", required = false) String timeToStr,
            Model model
    ) {
        LocalDateTime timeFrom;
        LocalDateTime timeTo;

        // TODO: to fix later and to add setting time in view
        if (timeFromStr == null || timeToStr == null) {
            timeFrom = LocalDateTime.now().minusDays(365);
            timeTo = LocalDateTime.now();
        } else {
            timeFrom = LocalDate.parse(timeFromStr).atStartOfDay();
            timeTo = LocalDate.parse(timeToStr).atStartOfDay();
        }

        appServ.generateChart(app, timeFrom, timeTo);
        model.addAttribute("app", app);
        model.addAttribute("timeFrom", timeFrom);
        model.addAttribute("timeTo", timeTo);

        return "/user/applicationDetail";
    }
}
