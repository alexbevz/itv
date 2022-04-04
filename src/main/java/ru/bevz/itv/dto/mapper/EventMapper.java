package ru.bevz.itv.dto.mapper;

import org.springframework.stereotype.Component;
import ru.bevz.itv.dto.EventDto;
import ru.bevz.itv.entity.Event;

@Component
public class EventMapper {

    public EventDto toEventDto(Event event) {
        return new EventDto()
                .setIdApplication(event.getApplication().getId())
                .setName(event.getName())
                .setDescription(event.getDescription());
    }

}
