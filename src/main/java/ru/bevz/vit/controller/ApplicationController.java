package ru.bevz.vit.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bevz.vit.domain.Application;
import ru.bevz.vit.domain.User;
import ru.bevz.vit.service.ApplicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
            RedirectAttributes redirectAttributes
    ) {

        if (name == null || name.isEmpty()) {
            redirectAttributes.addFlashAttribute("nameError", "название приложения не может быть пустым");
            return "redirect:/user/applications/add";
        } else {
            Application app = new Application();
            app.setName(name);
            appServ.addApplicationForUser(app, user);

            return "redirect:/user/applications";
        }

    }

    @GetMapping("/applications/{appId}")
    public String detailApplication(
            @AuthenticationPrincipal User user,
            @PathVariable long appId,
            @RequestParam(value = "timeFrom", required = false, defaultValue = "") String timeFromStr,
            @RequestParam(value = "timeTo", required = false, defaultValue = "") String timeToStr,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        Application app = appServ.getAppById(appId);

        if (app == null || app.getName() == null || !app.getUser().equals(user)) {
            redirectAttributes.addFlashAttribute(
                    "getAppError",
                    "у вас нет приложения с уникальным идентификатором " + appId
            );
            return "redirect:/user/applications";
        }
        LocalDateTime timeFrom;
        LocalDateTime timeTo;


        if (timeFromStr.isEmpty()) {
            timeFrom = LocalDateTime.now().minusYears(1);
            timeFromStr = timeFrom.format(DateTimeFormatter.ISO_DATE);
        } else {
            timeFrom = LocalDate.parse(timeFromStr).atStartOfDay();
        }

        if (timeToStr.isEmpty()) {
            timeTo = LocalDateTime.now();
            timeToStr = timeTo.format(DateTimeFormatter.ISO_DATE);
        } else {
            timeTo = LocalDate.parse(timeToStr).atTime(LocalTime.MAX);
        }

        if (timeFrom.isAfter(timeTo)) {
            model.addAttribute("timeError", "неверный порядок дат");
        } else {
            appServ.prepareDetailApplicationView(app, timeFrom, timeTo);
            model.addAttribute("customEvents", appServ.getDataForChart(app, timeFrom, timeTo).entrySet());
        }

        model.addAttribute("wmyEvents", appServ.getDataForWmyChart(app));
        model.addAttribute("app", app);
        model.addAttribute("timeFrom", timeFromStr);
        model.addAttribute("timeTo", timeToStr);

        return "/user/applicationDetail";
    }
}
