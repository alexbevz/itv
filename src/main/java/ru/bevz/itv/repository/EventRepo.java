package ru.bevz.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bevz.itv.domain.Event;

public interface EventRepo extends JpaRepository<Event, Long> {

}
