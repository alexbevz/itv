package ru.bevz.itv.service;

import org.springframework.stereotype.Service;
import ru.bevz.itv.domain.Application;
import ru.bevz.itv.domain.User;
import ru.bevz.itv.repository.ApplicationRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepo appRepo;


    public ApplicationService(ApplicationRepo appRepo) {
        this.appRepo = appRepo;
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
}
