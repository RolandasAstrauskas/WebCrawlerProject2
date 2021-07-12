package lt.rol;

import lt.rol.configuration.SeleniumConfiguration;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        WebCrawler webCrawler = new WebCrawler();
        webCrawler.searchForFlight();
    }
}
