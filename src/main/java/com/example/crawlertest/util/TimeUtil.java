package com.example.crawlertest.util;

import com.example.crawlertest.services.VacatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeUtil {

    @Autowired
    private VacatureService vacatureService;

//    @Scheduled(cron = "0 20 9 * * ?")
    public void maakVacatures() {
        vacatureService.maakVacatures();
    }
}
