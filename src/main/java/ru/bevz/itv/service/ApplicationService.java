package ru.bevz.itv.service;

import org.springframework.stereotype.Service;
import ru.bevz.itv.repository.ApplicationRepo;

@Service
public class ApplicationService {

    private final ApplicationRepo applicationRepo;


    public ApplicationService(ApplicationRepo applicationRepo) {
        this.applicationRepo = applicationRepo;
    }

//    public ApplicationDto addApplicationForUser(ApplicationDto applicationDto, User user) {
//        final String nameApplication = applicationDto.getName();
//        if (nameApplication.isEmpty()) {
//            throw new ValidationException("Не задано название приложения!");
//        }
//
//        final long idApplication = applicationDto.getId();
//        if (idApplication < 0) {
//            throw new ValidationException("Невозможный уникальный индентификатор: " + idApplication);
//        }
//
//        Application application;
//
//        if (idApplication == 0) {
//            application = new Application();
//            application.setUser(user);
//        } else {
//            Optional<Application> applicationOptional =
//                    applicationRepository.findApplicationByIdAndUser(idApplication, user);
//            if (applicationOptional.isPresent()) {
//                application = applicationOptional.get();
//            } else {
//                throw new IllegalStateException("Произошло что-то нереальное, попробуйте ещё раз :)");
//            }
//        }
//
//        application.setName(nameApplication);
//        application.setDtCreation(LocalDateTime.now());
//
//        application = applicationRepository.save(application);
//
//        return applicationMapper.toApplicationDto(application);
//    }
//
//    // Так как по условию сказано, что
//    // "Форма создания приложения должна содержать следующее поле:
//    //  ● уникальный идентификатор приложения;",
//    // следовательно, приложение следует создать до того,
//    // как будет отправлена форма создания приложения.
//    // Также следует рассмотреть вариант,
//    // что пользователь выполняет опять get-запрос для создания формы.
//    // Но так как в БД уже есть созданное приложение без названия, то следует его, а не новое генерировать.
//    public ApplicationDto preAddApplicationForUser(User user) {
//        // Ищем приложение без название (name = null) у текущего пользователя в БД.
//        Optional<Application> optionalApplication =
//                applicationRepository.findApplicationByUserAndNameIsNull(user);
//
//        // Если нашлось такое приложение, то возвращаем как черновик.
//        if (optionalApplication.isPresent()) {
//            return applicationMapper.toApplicationDto(optionalApplication.get());
//        }
//
//        // Иначе создаем и возвращаем приложение у текущего пользователя.
//        Application application = new Application();
//        application.setUser(user);
//        return applicationMapper.toApplicationDto(applicationRepository.save(application));
//    }
//
//    public List<ApplicationDto> getApplicationsByUser(User user) {
//
//        List<Application> applicationList =
//                applicationRepository.getApplicationsByUserAndNameIsNotNullOrderByDtCreation(user);
//
//        return applicationList.stream()
//                .map(applicationMapper::toApplicationDto)
//                .collect(Collectors.toList());
//    }
//
//    public ApplicationDto getApplicationByIdAndUser(long idApplication, User user) {
//        Application application =
//                applicationRepository.findApplicationByIdAndUserAndNameIsNotNull(idApplication, user);
//
//        if (application == null) {
//            throw new NullPointerException("Приложение с уникальным идентификатором "
//                    + idApplication +
//                    " не сущестует или принадлежит другому пользователю!");
//        }
//
//        return applicationMapper.toApplicationDto(application);
//    }

}
