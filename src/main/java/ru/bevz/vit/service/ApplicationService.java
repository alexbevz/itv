package ru.bevz.vit.service;

import au.com.bytecode.opencsv.CSVWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.bevz.vit.domain.Application;
import ru.bevz.vit.domain.Event;
import ru.bevz.vit.domain.User;
import ru.bevz.vit.domain.dto.EventCountByWmy;
import ru.bevz.vit.repository.ApplicationRepo;
import ru.bevz.vit.repository.EventRepo;
import ru.homyakin.iuliia.Translator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepo appRepo;

    private final EventRepo eventRepo;

    private final Translator translator;

    @Value("${download.path.img}")
    private String pathImg;

    @Value("${download.path.csv}")
    private String pathCsv;

    public ApplicationService(ApplicationRepo appRepo, EventRepo eventRepo, Translator translator) {
        this.appRepo = appRepo;
        this.eventRepo = eventRepo;
        this.translator = translator;
    }

    public Application addApplicationForUser(Application app, User user) {
        Application appOpt = appRepo.findApplicationByUserAndNameIsNull(user);
        if (appOpt != null) {
            app.setId(appOpt.getId());
        }
        app.setUser(user);
        app.setDtCreation(LocalDateTime.now());

        return appRepo.save(app);
    }

    public Application preAddApplicationForUser(User user) {
        Application app =
                appRepo.findApplicationByUserAndNameIsNull(user);

        if (app != null) {
            return app;
        }

        app = new Application();
        app.setUser(user);
        return appRepo.save(app);
    }

    public List<Application> getByUser(User user) {
        return appRepo.getApplicationsByUserAndNameIsNotNullOrderByDtCreation(user);
    }

    public void prepareDetailApplicationView(Application app, LocalDateTime from, LocalDateTime to) {
        LocalDateTime dateTimeGenerating = LocalDateTime.now().withNano(0);

        if (app.getFilenameCsv() != null && app.getFilenameChart() != null) {
            new File(pathCsv, app.getFilenameCsv()).delete();
            new File(pathImg, app.getFilenameChart()).delete();
        }

        app.setFilenameCsv("%s-data-app-%s-from-%s-to-%s.csv".formatted(
                dateTimeGenerating,
                translator.translate(app.getName()),
                from.toLocalDate(),
                to.toLocalDate()
        ).replace(':', '-').replace(' ', '-'));
        app.setFilenameChart("%s-chart-app-%s-from-%s-to-%s.png".formatted(
                dateTimeGenerating,
                translator.translate(app.getName()),
                from.toLocalDate(),
                to.toLocalDate()
        ).replace(':', '-').replace(' ', '-'));

        List<Event> events =
                eventRepo.findEventsByApplicationAndDtCreationBetweenOrderByDtCreation(app, from, to);

        generateCsv(app, events);
        generateChart(app, events);

        appRepo.save(app);
    }

    private void generateChart(Application app, List<Event> events) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String nameCategory = "Количество событий";

        events.stream()
                .collect(Collectors.groupingBy(Event::getName, Collectors.counting()))
                .forEach((k, v) -> dataset.setValue(v, nameCategory, k));

        JFreeChart chart = ChartFactory.createBarChart("", "", nameCategory, dataset);

        try {
            ChartUtils.saveChartAsPNG(new File(pathImg, app.getFilenameChart()), chart, 1200, 700);
        } catch (IOException e) {
            app.setName(null);
            throw new RuntimeException("IMG" + e);
        }
    }

    private void generateCsv(Application app, List<Event> events) {
        List<String[]> stringEvents = events
                .stream()
                .map(Event::getEventLikeArrayString)
                .toList();

        try {
            CSVWriter csvWriter =
                    new CSVWriter(new FileWriter(new File(pathCsv, app.getFilenameCsv())));
            csvWriter.writeAll(stringEvents);
            csvWriter.close();
        } catch (IOException e) {
            app.setFilenameCsv(null);
            throw new RuntimeException("CSV " + e);
        }
    }

    public long getCountAppsByUser(User user) {
        Optional<Long> countOptional = appRepo.countApplicationsByUserAndNameIsNotNull(user);

        if (countOptional.isPresent()) {
            return countOptional.get();
        } else {
            return 0;
        }
    }

    public Application getAppById(long appId) {
        return appRepo.findById(appId).orElse(null);
    }

    public Map<String, Long> getDataForChart(Application app, LocalDateTime timeFrom, LocalDateTime timeTo) {
        return eventRepo.findEventsByApplicationAndDtCreationBetweenOrderByDtCreation(app, timeFrom, timeTo)
                .stream()
                .collect(Collectors.groupingBy(Event::getName, Collectors.counting()));
    }

    public Set<EventCountByWmy> getDataForWmyChart(Application app) {
        LocalDateTime dtNow = LocalDateTime.now();
        Map<String, Long> week = getDataForChart(app, dtNow.minusWeeks(1), dtNow);
        Map<String, Long> month = getDataForChart(app, dtNow.minusMonths(1), dtNow);
        Map<String, Long> year = getDataForChart(app, dtNow.minusYears(1), dtNow);
        Set<EventCountByWmy> eventCountByWmySet = new HashSet<>();
        for (String key : year.keySet()) {
            eventCountByWmySet.add(
                    new EventCountByWmy(
                            key,
                            week.getOrDefault(key, 0L),
                            month.getOrDefault(key, 0L),
                            year.getOrDefault(key, 0L)
                    )
            );
        }
        return eventCountByWmySet;
    }

    public boolean existsAppById(long id) {
        return appRepo.existsById(id);
    }
}
