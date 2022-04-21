package ru.bevz.itv.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bevz.itv.domain.User;
import ru.bevz.itv.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class RegistrationController {

    private final UserService userService;

    private final ControllerUtils controllerUtils;

    @Value("${recaptcha.html}")
    private String captchaHtml;

    public RegistrationController(UserService userService, ControllerUtils controllerUtils) {
        this.userService = userService;
        this.controllerUtils = controllerUtils;
    }

    @ModelAttribute("captchaHtml")
    public String getCaptchaHtml() {
        return captchaHtml;
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpSession session) {

        if (session != null && session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null) {
            model.addAttribute("message", "неверные учетные данные");
            model.addAttribute("messageType", "danger");
            session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", null);
        }

        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        boolean validCaptcha = controllerUtils.checkCaptcha(captchaResponse);

        if (!validCaptcha) {
            model.addAttribute("captchaError", "заполните каптчу");
        }

        if (user.getPassword() != null && !user.getConfirmPassword().equals(user.getPassword())) {
            result.rejectValue("confirmPassword", "confirmPasswordError", "пароли разные");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            result.rejectValue("username", "usernameError", "пользователь с такой почтой существует");
        }

        if (result.hasErrors() || !validCaptcha) {
            model.mergeAttributes(controllerUtils.getErrors(result));
            return "registration";
        }

        userService.addUser(user);
        redirectAttributes.addFlashAttribute("message", "Пользователь успешно зарегистрирован! Подтвердите почту, перейдя по ссылке в письме");
        redirectAttributes.addFlashAttribute("messageType", "success");

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(
            @PathVariable String code,
            RedirectAttributes redirectAttributes
    ) {
        boolean isActivate = userService.activateUser(code);
        String message;
        String messageType;

        if (isActivate) {
            message = "Пользователь успешно подтвержден!";
            messageType = "success";
        } else {
            message = "Код активации не найден!";
            messageType = "danger";
        }
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("messageType", messageType);

        return "redirect:/login";
    }

}
