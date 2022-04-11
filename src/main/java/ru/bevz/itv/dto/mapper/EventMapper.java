package ru.bevz.itv.dto.mapper;

import org.springframework.stereotype.Component;
import ru.bevz.itv.controller.model.EventModel;
import ru.bevz.itv.dto.EventDto;
import ru.bevz.itv.domain.Event;

@Component
public class EventMapper {

    public EventDto toEventDto(Event event) {
        return new EventDto()
                .setIdApplication(event.getApplication().getId())
                .setName(event.getName())
                .setDescription(event.getDescription());
    }

    public EventDto toEventDto(EventModel eventModel) {
        return new EventDto()
                .setIdApplication(eventModel.getIdApplication())
                .setName(eventModel.getName())
                .setDescription(eventModel.getDescription());
    }

    public EventDto toEventDto(long idApplication, String nameEvent, String description) {
        return new EventDto()
                .setIdApplication(idApplication)
                .setName(nameEvent)
                .setDescription(description);
    }

}
