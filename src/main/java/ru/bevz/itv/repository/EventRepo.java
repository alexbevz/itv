package ru.bevz.itv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bevz.itv.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {

    @Query(
            value = """
                    select ev.name, count(ev.name)
                    from Event as ev
                    where ev.application.id = :appId and ev.dtCreation between :from and :to
                    group by ev.name
                    """
    )
    List<Object[]> findByAppIdAndDtCreationBetweenCountGroupByName(
            long appId,
            LocalDateTime from,
            LocalDateTime to
    );
}
