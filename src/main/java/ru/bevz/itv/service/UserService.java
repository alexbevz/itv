package ru.bevz.itv.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.bevz.itv.controller.model.UserRegistrationModel;
import ru.bevz.itv.entity.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationModel registration);
}