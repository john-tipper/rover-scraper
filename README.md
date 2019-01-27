# Minimal demo of scraping data from DVLA vehicle database website

This project uses [PhantomJS](http://phantomjs.org) to download data from the DVLA website and render from Javascript into HTML.  It then uses [JSoup](https://jsoup.org) to parse that HTML into a DOM that can be processed.

## Code

All functionality is contained within the [Crawler](src/main/java/com/example/Crawler.java) class - there are no tests and the registration being sought is hardcoded.
 
## Running

    ./gradlew run  

## Warning

**NOTE:** The website seems to occasionally return no results, even when the vehicle exists in the database.  I don't know whether this is due to some form of rate-limiting at the server end, or whether there's some funny interaction between PhantomJS and the Java process that is calling it.