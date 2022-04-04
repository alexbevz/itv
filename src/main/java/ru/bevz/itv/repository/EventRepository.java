package ru.bevz.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bevz.itv.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
