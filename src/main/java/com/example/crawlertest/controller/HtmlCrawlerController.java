package com.example.crawlertest.controller;

import com.example.crawlertest.domein.HtmlCrawler;
import com.example.crawlertest.domein.Zoekopdracht;
import com.example.crawlertest.services.VacatureService;
import com.example.crawlertest.services.ZoekopdrachtService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.apache.http.client.config.CookieSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "crawl")
public class HtmlCrawlerController {

    private ZoekopdrachtService zoekopdrachtService;
    private VacatureService vacatureService;

    @Autowired
    public HtmlCrawlerController(ZoekopdrachtService zoekopdrachtService, VacatureService vacatureService) {
        this.zoekopdrachtService = zoekopdrachtService;
        this.vacatureService = vacatureService;
    }

    @PostMapping("/")
    public void crawlWebsite(@RequestBody Zoekopdracht zoekopdracht) {
        System.out.println(zoekopdracht);
        File crawlOpslag = new File("src/main/resources/crawlerOpslag");
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlOpslag.getAbsolutePath());
        config.setMaxDepthOfCrawling(2);
        config.setIncludeHttpsPages(true);

        int numCrawlers = 10000;

        PageFetcher fetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, fetcher);

        try {
            CrawlController controller = new CrawlController(config, fetcher, robotstxtServer);
            controller.addSeed(zoekopdracht.getWebsite());
            zoekopdracht.setResultaten(new ArrayList<>());

            CrawlController.WebCrawlerFactory<HtmlCrawler> factory = () -> new HtmlCrawler(zoekopdracht);
            controller.start(factory, numCrawlers);

            zoekopdrachtService.zoekopdrachtOpslaan(zoekopdracht);
            vacatureService.maakVacatures();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
