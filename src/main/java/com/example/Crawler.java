package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crawler {
    private static Logger LOG = LoggerFactory.getLogger(Crawler.class.getName());
    private static String dvlaUrl = "https://vehicleenquiry.service.gov.uk/ConfirmVehicle?Vrm=";

    WebDriver driver;

    public Crawler() {
        System.setProperty("phantomjs.binary.path", "libs/phantomjs");
        driver = new PhantomJSDriver();
    }

    public void startCrawl() {
        try {
            String registration = "HUE166";
            driver.get(dvlaUrl + registration);
            Document document = Jsoup.parse(driver.getPageSource());
            Element results = document.selectFirst(".list-summary");
            // note that sometimes the DVLA website seems to return no results even when the vehicle exists!
            if (results != null) {
                Elements dataItems = results.select(".list-summary-item");
                // need to parse into Elements, but there is only one make
                Elements make = dataItems.select(":contains(Make)").select("strong");
                LOG.debug("Make: '" + make.text() + "'");

                // need to parse into Elements, but there is only one colour
                Elements colour = dataItems.select(":contains(Colour)").select("strong");
                LOG.debug("Colour: '" + colour.text() + "'");

            } else {
                LOG.debug("No results for registration " + registration);
            }

        }
        catch(Exception ex) {
            // ignore, just log
            LOG.error(ex.getMessage() + ex.getStackTrace());
        }
        finally {
            if (driver != null) {
                driver.quit();
            }
        }

    }
}
