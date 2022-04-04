package ru.bevz.itv.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
public class ApplicationDto {

    long id;

    String name;

}
