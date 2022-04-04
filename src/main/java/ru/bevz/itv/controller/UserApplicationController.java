package ru.bevz.itv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bevz.itv.controller.model.ApplicationModel;
import ru.bevz.itv.dto.ApplicationDto;
import ru.bevz.itv.dto.mapper.ApplicationMapper;
import ru.bevz.itv.service.ApplicationService;

@Controller
@RequestMapping("/user")
public class UserApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationMapper applicationMapper;

    @GetMapping("/applications")
    public String getListApplications(Model model) {
        model.addAttribute("applications", applicationService.getApplicationsByCurrentUser());
        return "/user/applications";
    }

    @GetMapping("/applications/add")
    public String addApplication(Model model) {
        ApplicationDto applicationDto = applicationService.preAddApplicationForCurrentUser();
        model.addAttribute("app", applicationMapper.toApplicationModel(applicationDto));
        return "/user/add-app";
    }

    @PostMapping("/applications/add")
    public String addApplication(
            @ModelAttribute("application") ApplicationModel applicationModel,
            BindingResult result
    ) {
        if (applicationModel.getName().isEmpty()) {
            result.rejectValue("name", null, "Пустое приложение, задайте название!");
        }

        if (result.hasErrors()) {
            return "/user/add-app";
        }

        applicationService.addApplicationForCurrentUser(
                new ApplicationDto()
                        .setId(applicationModel.getId())
                        .setName(applicationModel.getName())
        );


        return "redirect:/user/applications";
    }

    @GetMapping("/applications/{id}")
    public String detailApplication(
            @PathVariable long id,
            Model model
    ) {
        ApplicationDto applicationDto = applicationService.getApplicationByCurrentUser(id);
        model.addAttribute("app", applicationMapper.toApplicationModel(applicationDto));

        return "/user/app-detail";
    }
}
