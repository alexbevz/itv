package ru.bevz.itv.service;

import org.springframework.stereotype.Service;
import ru.bevz.itv.dto.EventDto;
import ru.bevz.itv.dto.mapper.EventMapper;
import ru.bevz.itv.domain.Application;
import ru.bevz.itv.domain.Event;
import ru.bevz.itv.repository.EventRepository;

import java.time.LocalDateTime;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDto addEvent(EventDto eventDto) {
        final long applicationId = eventDto.getIdApplication();

        //TODO: to need to check for existence Application in DB
//        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
//        if (applicationOptional.isEmpty()) {
//            throw new EntityExistsException("Not exists application with id = " + applicationId);
//        }

        Event event = new Event(
                eventDto.getName(),
                eventDto.getDescription(),
                new Application(applicationId),
                LocalDateTime.now()
        );

        event = eventRepository.save(event);

        return eventMapper.toEventDto(event);
    }
}
