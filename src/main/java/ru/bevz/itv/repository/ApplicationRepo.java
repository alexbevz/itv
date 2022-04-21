package ru.bevz.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bevz.itv.domain.Application;
import ru.bevz.itv.domain.User;

import java.util.List;

public interface ApplicationRepo extends JpaRepository<Application, Long> {

    List<Application> getApplicationsByUserAndNameIsNotNullOrderByDtCreation(User user);

    Application findApplicationByUserAndNameIsNull(User user);

}
