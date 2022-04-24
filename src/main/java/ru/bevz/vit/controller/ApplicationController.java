package ru.bevz.vit.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bevz.vit.domain.Application;
import ru.bevz.vit.domain.User;
import ru.bevz.vit.service.ApplicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
            @RequestParam(value = "timeFrom", required = false, defaultValue = "") String timeFromStr,
            @RequestParam(value = "timeTo", required = false, defaultValue = "") String timeToStr,
            Model model
    ) {
        LocalDateTime timeFrom;
        LocalDateTime timeTo;


        if (timeFromStr.isEmpty()) {
            timeFrom = LocalDateTime.of(2000, 1, 1, 1, 1);
        } else {
            timeFrom = LocalDate.parse(timeFromStr).atStartOfDay();
        }

        if (timeFromStr.isEmpty()) {
            timeTo = LocalDateTime.now();
        } else {
            timeTo = LocalDate.parse(timeToStr).atTime(LocalTime.MAX);
        }

        if (timeFrom.isAfter(timeTo)) {
            model.addAttribute("timeError", "неверный порядок дат");
        } else {
            appServ.prepareDetailApplicationView(app, timeFrom, timeTo);
        }

        model.addAttribute("app", app);
        model.addAttribute("timeFrom", timeFromStr);
        model.addAttribute("timeTo", timeToStr);

        return "/user/applicationDetail";
    }
}
