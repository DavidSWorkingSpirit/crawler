package com.example.crawlertest.domein;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class HtmlCrawler extends WebCrawler {

    private final static Pattern UITZONDERINGEN = Pattern.compile(
                    ".*(\\.(css|js|ts|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz|txt|svg))$");
//    private final static Pattern VACATURE = Pattern.compile(".*(\\.(/vacature/"+ "))$");

    private final Logger LOGGER = Logger.getLogger("HtmlCrawlerLog");

    private Zoekopdracht zoekopdracht;
    private CallBack callBack;

    public HtmlCrawler(Zoekopdracht zoekopdracht, CallBack callBack) {

        this.zoekopdracht = zoekopdracht;
        this.callBack = callBack;
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
            String html = htmlParseData.getHtml();
            Document document = Jsoup.parse(html);

            String content = document.body().text();
            String titel = htmlParseData.getTitle();
            String tekst = htmlParseData.getText();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            if (content.toLowerCase().contains(zoekopdracht.getZoekterm().toLowerCase()) &&
                    Pattern.compile("vacature/\\d{7}").matcher(url).find()) {

                resultaat.setTitel(titel);
                resultaat.setTekst(content);
                resultaat.setUrl(url);
                resultaat.setZoekopdracht(zoekopdracht);

                callBack.verwerkResultaat(resultaat);
            }
        }
    }
}
