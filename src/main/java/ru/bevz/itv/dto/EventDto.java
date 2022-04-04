package ru.bevz.itv.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
public class EventDto {

    long idApplication;

    String name;

    String description;
}
