package ru.bevz.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bevz.itv.domain.Application;
import ru.bevz.itv.domain.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application, Long> {

    Optional<Application> findApplicationByIdAndUser(long id, User user);

    Application findApplicationByIdAndUserAndNameIsNotNull(long id, User user);

    List<Application> getApplicationsByUserAndNameIsNotNullOrderByDtCreation(User user);

    Optional<Application> findApplicationByUserAndNameIsNull(User user);
}
