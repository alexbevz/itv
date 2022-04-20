package ru.bevz.itv.controller.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class EventModel {

    @NotEmpty(message = "уникальный индентификатор приложения не может быть пустым")
    private long idApplication;

    @NotEmpty(message = "название события не может быть пустым")
    private String name;

    @NotEmpty(message = "описание события не может быть пустым")
    private String description;

}
