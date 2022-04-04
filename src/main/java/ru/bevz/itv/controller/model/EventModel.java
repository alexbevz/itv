package ru.bevz.itv.controller.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class EventModel {

    @NotBlank
    private long idApplication;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

}
