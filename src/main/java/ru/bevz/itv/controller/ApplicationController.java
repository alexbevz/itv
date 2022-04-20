package ru.bevz.itv.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bevz.itv.controller.model.ApplicationModel;
import ru.bevz.itv.domain.Application;
import ru.bevz.itv.domain.User;
import ru.bevz.itv.service.ApplicationService;

@Controller
@RequestMapping("/user")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/applications")
    public String getListApplications(
            @AuthenticationPrincipal User user,
            Model model
    ) {
//        model.addAttribute("applications", applicationService.getApplicationsByUser(user));
        return "/user/applications";
    }

    @GetMapping("/applications/add")
    public String getFormForAddApplication(
            @AuthenticationPrincipal User user,
            Model model
    ) {
//        ApplicationDto applicationDto = applicationService.preAddApplicationForUser(user);
//        model.addAttribute("application", applicationMapper.toApplicationModel(applicationDto));

        return "/user/applicationAdd";
    }

    @PostMapping("/applications")
    public String addApplication(
            @AuthenticationPrincipal User user,
            @ModelAttribute("application") ApplicationModel applicationModel,
            BindingResult result
    ) {
        if (applicationModel.getName().isEmpty()) {
            result.rejectValue("name", null, "Пустое приложение, задайте название!");
        }

        if (result.hasErrors()) {
            return "/user/applicationAdd";
        }

//        ApplicationDto applicationDto = new ApplicationDto();
//        applicationDto.setId(applicationModel.getId());
//        applicationDto.setName(applicationModel.getName());
//
//        applicationDto = applicationService.addApplicationForUser(applicationDto, user);


        return "redirect:/user/applications";
    }

    @GetMapping("/applications/{application}")
    public String detailApplication(
            @AuthenticationPrincipal User user,
            @PathVariable Application application,
            Model model
    ) {
//        ApplicationDto applicationDto =
//                applicationService.getApplicationByIdAndUser(application.getId(), user);
//        model.addAttribute("application", applicationMapper.toApplicationModel(applicationDto));

        return "/user/applicationDetail";
    }
}
