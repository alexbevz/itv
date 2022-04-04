package ru.bevz.itv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bevz.itv.controller.model.UserRegistrationModel;
import ru.bevz.itv.entity.User;
import ru.bevz.itv.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

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
            HttpServletRequest request,
            BindingResult result
    ) {

        User existing = userService.findByEmail(userModel.getEmail());
        if (existing != null) {
            result.rejectValue(
                    "email",
                    null,
                    "Аккаунт c такой элекстронной почтой уже зарегистрирован!"
            );
        }

        if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
            result.rejectValue(
                    "confirmPassword",
                    null,
                    "Пароли не совпадают, повторите попытку!"
            );
        }

        if (!userModel.getEmail().matches(".+@.+")) {
            result.rejectValue(
                    "email",
                    null,
                    "Предположительно не являестся адресом электронной почты!"
            );
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userModel);

        try {
            request.login(userModel.getEmail(), userModel.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:/user/applications";
    }
}
