package ru.bevz.itv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bevz.itv.controller.model.UserRegistrationModel;
import ru.bevz.itv.domain.User;
import ru.bevz.itv.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationModel userRegistrationModel() {
        return new UserRegistrationModel();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("user") UserRegistrationModel userModel,
            Model model,
            BindingResult result
    ) {

        User existing = userService.findByEmail(userModel.getEmail());

        //TODO: to need to handler some errors in views
        if (existing != null) {
            result.rejectValue(
                    "email",
                    null,
                    "Аккаунт c такой элекстронной почтой уже зарегистрирован!"
            );
        }

        if (!userModel.getEmail().matches(".+@.+")) {
            result.rejectValue(
                    "email",
                    null,
                    "Предположительно не являестся адресом электронной почты!"
            );
        }

        if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
            result.rejectValue(
                    "confirmPassword",
                    null,
                    "Пароли не совпадают, повторите попытку!"
            );
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userModel);

        return "redirect:/login";
    }
}
