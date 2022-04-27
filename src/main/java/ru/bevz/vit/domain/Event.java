package ru.bevz.vit.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "\"event\"")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "обязательное поле для заполнения")
    private String name;
    @NotBlank(message = "обязательное поле для заполнения")
    private String description;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(name = "dt_creation")
    private LocalDateTime dtCreation;

    public String[] getEventLikeArrayString() {
        return new String[]{
                Long.toString(id),
                name,
                description,
                application.getName(),
                dtCreation.toString()
        };
    }

}