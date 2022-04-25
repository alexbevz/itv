package ru.bevz.vit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bevz.vit.domain.Role;
import ru.bevz.vit.domain.User;
import ru.bevz.vit.repository.UserRepo;

import javax.persistence.EntityExistsException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;

    @Value("${hostnameapp}")
    private String hostname;

    public UserService(UserRepo userRepo, EmailSenderService emailSenderService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.emailSenderService = emailSenderService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepo.findByUsername(username);

        if (byUsername == null) {
            throw new UsernameNotFoundException("User already exists");
        }

        return byUsername;
    }

    public void addUser(User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new EntityExistsException("user already exists");
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        sendMessage(user);
    }

    private void sendMessage(User user) {

        String message = "Hello, " + user.getUsername() + "! Your code activation: " + user.getActivationCode()
                + "\n Or you may visit the next link: http://%s/activate/".formatted(hostname) + user.getActivationCode()
                + "\n Thanks for attention!";
        Thread thread = new Thread(() -> emailSenderService.send(user.getUsername(), "Activation code", message));
        thread.start();

    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }


    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
