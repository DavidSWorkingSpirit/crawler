package com.example.crawlertest.controller;

import com.example.crawlertest.domein.CallBack;
import com.example.crawlertest.domein.HtmlCrawler;
import com.example.crawlertest.domein.Resultaat;
import com.example.crawlertest.domein.Zoekopdracht;
import com.example.crawlertest.services.ResultaatService;
import com.example.crawlertest.services.VacatureService;
import com.example.crawlertest.services.ZoekopdrachtService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "crawl")
public class HtmlCrawlerController {

    private ZoekopdrachtService zoekopdrachtService;
    private VacatureService vacatureService;
    private ResultaatService resultaatService;

    @Autowired
    public HtmlCrawlerController(ZoekopdrachtService zoekopdrachtService, VacatureService vacatureService,
                                ResultaatService resultaatService) {

        this.zoekopdrachtService = zoekopdrachtService;
        this.vacatureService = vacatureService;
        this.resultaatService = resultaatService;
    }

    @PostMapping("/")
    public void crawlWebsite(@RequestBody Zoekopdracht zoekopdracht) {

        File crawlOpslag = new File("src/main/resources/crawlerOpslag");
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlOpslag.getAbsolutePath());
        config.setMaxDepthOfCrawling(2);
        config.setIncludeHttpsPages(true);
        config.setCleanupDelaySeconds(60);
        config.setThreadShutdownDelaySeconds(60);
        config.setIncludeBinaryContentInCrawling(false);
        config.setThreadMonitoringDelaySeconds(60);

        final int numCrawlers = 10000;

        PageFetcher fetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, fetcher);

        try {
            CrawlController crawlManager = new CrawlController(config, fetcher, robotstxtServer);
            crawlManager.addSeed(zoekopdracht.getWebsite());
            zoekopdracht.setResultaten(new ArrayList<>());
            zoekopdrachtService.zoekopdrachtOpslaan(zoekopdracht);

            CrawlController.WebCrawlerFactory<HtmlCrawler> factory = () -> new HtmlCrawler(zoekopdracht,
                    resultaat -> resultaatService.resultaatOpslaan(resultaat));
            crawlManager.startNonBlocking(factory, numCrawlers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
