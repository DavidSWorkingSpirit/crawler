package com.example.crawlertest.util;

import com.example.crawlertest.services.CrawlService;
import com.example.crawlertest.services.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ScheduleUtil {

    @Autowired
    private CrawlService crawlService;

    @Autowired
    private WebsiteService websiteService;

    private final Logger LOGGER = Logger.getLogger("ScheduleUtilLog");

    @Scheduled(cron = "0 24 14 * * ?")
    public void crawl() {
        if (websiteService.geefAlleWebsites().size() > 0) {
            crawlService.crawlWebsites();
        } else {
            LOGGER.info("Geen websites gevonden om te crawlen");
        }
    }
}
