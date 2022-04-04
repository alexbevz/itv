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

    private long id;

    @NotEmpty
    private String name;

}
