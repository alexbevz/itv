package ru.bevz.itv.service;

import ru.bevz.itv.dto.ApplicationDto;
import ru.bevz.itv.dto.EventDto;

import java.util.List;

public interface ApplicationService {

    ApplicationDto addApplicationForCurrentUser(ApplicationDto applicationDto);

    ApplicationDto preAddApplicationForCurrentUser();

    List<ApplicationDto> getApplicationsByCurrentUser();

    ApplicationDto getApplicationByCurrentUser(long idApplication);

    EventDto addEvent(EventDto eventDto);
}
