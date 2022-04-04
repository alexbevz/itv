package ru.bevz.itv.dto.mapper;

import org.springframework.stereotype.Component;
import ru.bevz.itv.controller.model.ApplicationModel;
import ru.bevz.itv.dto.ApplicationDto;
import ru.bevz.itv.entity.Application;

@Component
public class ApplicationMapper {

    public ApplicationDto toApplicationDto(Application application) {
        return new ApplicationDto()
                .setId(application.getId())
                .setName(application.getName());
    }

    public ApplicationModel toApplicationModel(ApplicationDto applicationDto) {
        return new ApplicationModel()
                .setId(applicationDto.getId())
                .setName(applicationDto.getName());
    }

}
