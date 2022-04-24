package ru.bevz.vit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${download.path.img}")
    private String pathImg;

    @Value("${download.path.csv}")
    private String pathCsv;

    @Value("${path.static}")
    private String pathStatic;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String file = "file:///";

        registry.addResourceHandler("/static/**")
                .addResourceLocations(file + pathStatic + "/");
        new File(pathStatic).mkdirs();

        registry.addResourceHandler("/img/**")
                .addResourceLocations(file + pathImg + "/");
        new File(pathImg).mkdirs();

        registry.addResourceHandler("/csv/**")
                .addResourceLocations(file + pathCsv + "/");
        new File(pathCsv).mkdirs();
    }

}
