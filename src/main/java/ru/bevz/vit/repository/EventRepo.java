package ru.bevz.vit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bevz.vit.domain.Application;
import ru.bevz.vit.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {

    List<Event> findEventsByApplicationAndDtCreationBetweenOrderByDtCreation(Application app, LocalDateTime from, LocalDateTime to);

}
