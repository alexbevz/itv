package ru.bevz.vit.controller.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EventDto {
    @NotBlank
    private String name;
    @NotBlank
    @JsonProperty("application_id")
    private long applicationId;
    @NotBlank
    private String description;
}
