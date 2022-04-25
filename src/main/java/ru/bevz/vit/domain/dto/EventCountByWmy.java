package ru.bevz.vit.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventCountByWmy {
    private String name;
    private long countLastWeek;
    private long countLastMonth;
    private long countLastYear;
}
