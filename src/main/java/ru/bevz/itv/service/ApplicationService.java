package ru.bevz.itv.service;

import ru.bevz.itv.dto.ApplicationDto;
import ru.bevz.itv.domain.User;

import java.util.List;

public interface ApplicationService {

    ApplicationDto addApplicationForUser(ApplicationDto applicationDto, User user);

    ApplicationDto preAddApplicationForUser(User user);

    List<ApplicationDto> getApplicationsByUser(User user);

    ApplicationDto getApplicationByIdAndUser(long idApplication, User user);

}
