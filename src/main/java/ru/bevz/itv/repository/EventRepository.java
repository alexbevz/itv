package ru.bevz.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bevz.itv.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
