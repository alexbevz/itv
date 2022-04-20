package ru.bevz.itv.controller.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
public class ApplicationModel {

    @NotEmpty(message = "уникальный индентификатор приложения не может быть пустым")
    private long id;

    @NotEmpty(message = "название приложения не может быть пустым")
    private String name;

}
