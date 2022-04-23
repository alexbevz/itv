package ru.bevz.itv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.homyakin.iuliia.Schemas;
import ru.homyakin.iuliia.Translator;

@Configuration
public class UtilConfig {

    @Bean
    public Translator translator() {
        return new Translator(Schemas.ICAO_DOC_9303);
    }
}
