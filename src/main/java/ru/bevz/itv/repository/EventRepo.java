package ru.bevz.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bevz.itv.domain.Application;
import ru.bevz.itv.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {

    List<Event> findEventsByApplicationAndDtCreationBetweenOrderByDtCreation(Application app, LocalDateTime from, LocalDateTime to);

}
