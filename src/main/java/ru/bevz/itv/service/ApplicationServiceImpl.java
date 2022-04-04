package ru.bevz.itv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.bevz.itv.dto.ApplicationDto;
import ru.bevz.itv.dto.EventDto;
import ru.bevz.itv.dto.mapper.ApplicationMapper;
import ru.bevz.itv.dto.mapper.EventMapper;
import ru.bevz.itv.entity.Application;
import ru.bevz.itv.entity.Event;
import ru.bevz.itv.entity.User;
import ru.bevz.itv.repository.ApplicationRepository;
import ru.bevz.itv.repository.EventRepository;
import ru.bevz.itv.repository.UserRepository;

import javax.persistence.EntityExistsException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public ApplicationDto addApplicationForCurrentUser(ApplicationDto applicationDto) {
        final String nameApplication = applicationDto.getName();
        if (nameApplication.isEmpty()) {
            throw new NullPointerException("Не задано название приложения!");
        }

        final long idApplication = applicationDto.getId();
        if (idApplication < 0) {
            throw new ValidationException("Невозможный уникальный индентификатор: " + idApplication);
        }

        Application application;
        User user = getCurrentUser();

        if (idApplication == 0) {
            application = new Application().setUser(user);
        } else {
            Optional<Application> applicationOptional =
                    applicationRepository.findApplicationByIdAndUser(idApplication, user);
            if (applicationOptional.isPresent()) {
                application = applicationOptional.get();
            } else {
                throw new IllegalStateException("Произошло что-то нереальное, попробуйте ещё раз :)");
            }
        }

        application
                .setName(nameApplication)
                .setDtCreation(LocalDateTime.now());

        application = applicationRepository.save(application);

        return applicationMapper.toApplicationDto(application);
    }

    @Override
    public ApplicationDto preAddApplicationForCurrentUser() {
        Optional<Application> optionalApplication =
                applicationRepository.findApplicationByUserAndName(getCurrentUser(), null);

        if (optionalApplication.isPresent()) {
            return applicationMapper.toApplicationDto(optionalApplication.get());
        }
        return applicationMapper.toApplicationDto(
                applicationRepository.save(new Application().setUser(getCurrentUser()))
        );
    }

    @Override
    public List<ApplicationDto> getApplicationsByCurrentUser() {
        User user = getCurrentUser();

        List<Application> applicationList =
                applicationRepository.getApplicationsByUserAndNameIsNotNullOrderByDtCreation(user);

        return applicationList.stream()
                .map(applicationMapper::toApplicationDto)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDto getApplicationByCurrentUser(long idApplication) {
        User user = getCurrentUser();

        Optional<Application> application =
                applicationRepository.findApplicationByIdAndUserAndNameIsNotNull(idApplication, user);

        if (application.isEmpty()) {
            throw new NullPointerException("Приложение с уникальным идентификатором "
                    + idApplication +
                    " не сущестует или принадлежит другому пользователю!");
        }

        return applicationMapper.toApplicationDto(application.get());
    }

    @Override
    public EventDto addEvent(EventDto eventDto) {
        long applicationId = eventDto.getIdApplication();

        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isEmpty()) {
            throw new EntityExistsException("Not exists application with id = " + applicationId);
        }

        Event event = new Event()
                .setApplication(applicationOptional.get())
                .setName(eventDto.getName())
                .setDescription(eventDto.getDescription())
                .setDtCreation(LocalDateTime.now());

        event = eventRepository.save(event);

        return eventMapper.toEventDto(event);
    }

    private User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> userOptional = userRepository.findByEmail(userEmail);

        if (userOptional.isEmpty()) {
            throw new EntityExistsException("Пользователя с email " + userEmail + " не существует!");
        }
        return userOptional.get();
    }

}
