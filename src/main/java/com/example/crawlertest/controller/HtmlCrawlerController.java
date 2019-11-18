package com.example.crawlertest.controller;

import com.example.crawlertest.domein.HtmlCrawler;
import com.example.crawlertest.domein.Zoekopdracht;
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
import java.util.ArrayList;

@RestController
@RequestMapping(path = "crawl")
public class HtmlCrawlerController {

    private ZoekopdrachtService zoekopdrachtService;

    @Autowired
    public HtmlCrawlerController(ZoekopdrachtService zoekopdrachtService) {
        this.zoekopdrachtService = zoekopdrachtService;
    }

    @PostMapping("/")
    public void crawlWebsite(@RequestBody Zoekopdracht zoekopdracht) {
        File crawlOpslag = new File("src/main/resources/crawlerOpslag");
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlOpslag.getAbsolutePath());
        config.setMaxDepthOfCrawling(2);
        config.setIncludeHttpsPages(true);

        int numCrawlers = 100;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
