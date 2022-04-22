package ru.bevz.itv.service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.bevz.itv.domain.Application;
import ru.bevz.itv.domain.User;
import ru.bevz.itv.repository.ApplicationRepo;
import ru.bevz.itv.repository.EventRepo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService {

    private final ApplicationRepo appRepo;

    private final EventRepo eventRepo;

    @Value("${path.upload.img}")
    private String pathImg;


    public ApplicationService(ApplicationRepo appRepo, EventRepo eventRepo) {
        this.appRepo = appRepo;
        this.eventRepo = eventRepo;
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

    public void generateChart(Application app, LocalDateTime from, LocalDateTime to) {
        if (app.getChart() != null) {
            File fileChart = new File(pathImg + "/" + app.getChart());
            if (fileChart.exists()) {
                if (fileChart.delete()) {
                    app.setChart(null);
                }
            }
        }
        String eventRequest = "Запросы по событиям";
        List<Object[]> dataForChart =
                eventRepo.findByAppIdAndDtCreationBetweenCountGroupByName(app.getId(), from, to);
        var categoryDataset = new DefaultCategoryDataset();
        dataForChart.forEach((o) -> categoryDataset.setValue((Number) o[1], eventRequest, (String) o[0]));

        // TODO: to set properties for the best view
        JFreeChart chart = ChartFactory.createBarChart(
                null,
                "",
                eventRequest,
                categoryDataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                true
        );

        String nameChart = UUID.randomUUID().toString() + ".png";
        app.setChart(nameChart);

        try {
            ChartUtils.saveChartAsPNG(new File(pathImg + "/" + app.getChart()), chart, 1200, 700);
        } catch (IOException e) {
            app.setName(null);
            throw new RuntimeException(e);
        }
    }
}
