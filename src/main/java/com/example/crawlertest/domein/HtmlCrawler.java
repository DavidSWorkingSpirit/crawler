package com.example.crawlertest.domein;

import com.example.crawlertest.repositories.ResultaatRepository;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class HtmlCrawler extends WebCrawler {


    private final static Pattern UITZONDERINGEN = Pattern.compile(
                    ".*(\\.(css|js|ts|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz|txt|svg))$");
    private final static Pattern VACATURE = Pattern.compile(".*(\\.(/vacature/"+ "))$");
    private final Logger LOGGER = Logger.getLogger("HtmlCrawlerLog");
    private Zoekopdracht zoekopdracht;

    public HtmlCrawler(Zoekopdracht zoekopdracht) {
        this.zoekopdracht = zoekopdracht;
    }

    @Override
    public boolean shouldVisit(Page referentiePagina, WebURL url) {
        String urlString = url.getURL().toLowerCase();
        return !UITZONDERINGEN.matcher(urlString).matches();
//                && urlString.startsWith(urlString);
    }

    @Override
    public void visit(Page website) {
        String url = website.getWebURL().getURL();
        Resultaat resultaat = new Resultaat();

        if (website.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) website.getParseData();
            String titel = htmlParseData.getTitle();
            String tekst = htmlParseData.getText();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            if (tekst.contains(zoekopdracht.getZoekterm()) && tekst.contains("Solliciteer direct bij de werkgever")) {
                resultaat.setTitel(titel);
                resultaat.setTekst(url);
                resultaat.setZoekopdracht(zoekopdracht);
                System.out.println(url);

                zoekopdracht.getResultaten().add(resultaat);
                LOGGER.info("Nieuw resultaat gevonden: " + titel);
            }
        }
    }
}
