package ru.bevz.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bevz.itv.entity.Application;
import ru.bevz.itv.entity.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findById(long id);

    Optional<Application> findApplicationByIdAndUser(long id, User user);

    Optional<Application> findApplicationByIdAndUserAndNameIsNotNull(long id, User user);

    List<Application> getApplicationsByUserAndNameIsNotNullOrderByDtCreation(User user);

    Optional<Application> findApplicationByUserAndName(User user, String name);
}
