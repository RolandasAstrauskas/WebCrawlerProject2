package lt.rol;

import lt.rol.configuration.SeleniumConfiguration;
import lt.rol.flightData.Flight;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;


public class WebCrawler {

    private final  SeleniumConfiguration seleniumConfiguration = new SeleniumConfiguration();
    private final WebDriver driver = seleniumConfiguration.getDriver();

    public void searchForFlight() throws InterruptedException {
        insertFlightFrom();
        insertFlightTo();
        insertFlightDateFrom();
        insertFlightDateTo();
        searchButtonClick();
        readDataFromTables();
        driver.close();
    }

    private void readDataFromTables() {
        ArrayList<Flight> flightArrayList = new ArrayList<>();
        int rowStart = 2;
        int howManyFlight;
        String flightLastAirport;

        for(int twoTables = 2; 4 > twoTables; twoTables++) {

            while (chooseFlight(rowStart, twoTables)) {
                String price = getCheapestPrice(rowStart, twoTables);
                String departureAndArrivalTime = getTimeDepartureAndArrival(rowStart, twoTables);
                howManyFlight = checkIfItsConnectedFlight(rowStart, twoTables);
                String flightFrom = getFlightFrom(rowStart, howManyFlight, twoTables);
                String flightTo = getFlightTo(rowStart, howManyFlight, twoTables);

                if (flightTo.equals("Oslo)")) {
                    flightLastAirport = getFlightToLastAirport(rowStart, twoTables);
                } else {
                    flightLastAirport = "";
                }
                flightArrayList.add(new Flight(departureAndArrivalTime, flightFrom, flightTo, flightLastAirport, price));

                rowStart = rowStart + 3;
            }
        }
    }

    private int checkIfItsConnectedFlight(int rowStart, int twoTables) {
        int trNumber;

        try{driver.findElement(By.xpath("/html/body/form[6]/div[1]/div/div[1]/div[2]/div/div[1]/div[3]/div/div[" + twoTables +"]" +
                        "/div[1]/div/div/table/tbody/tr[" + rowStart + "]/td/div/table/tbody/tr[8]/td"));
            trNumber = 2;
        }catch (Exception e){
            trNumber = 3;
        }
        return trNumber;
    }

    private String getTimeDepartureAndArrival(int rowNumber, int twoTables) {
        return driver.findElement(By.xpath("/html/body/form[6]/div[1]/div/div[1]/div[2]/div/div[1]/div[3]/div/div[" + twoTables +
                "]/div[1]/div/div/table/tbody/tr[" + rowNumber + "]/td[7]")).getText();
    }

    private String getFlightToLastAirport(int rowStart, int twoTables) {
        return driver.findElement(By.xpath("/html/body/form[6]/div[1]/div/div[1]/div[2]/div/div[1]/div[3]/div/div[" + twoTables +
                "]/div[1]/div/div/table/tbody/tr[5]/td/div/table/tbody/tr[" + rowStart + "]/td[3]/span[3]")).getText();
    }

    private String getFlightTo(int rowStart, int howManyFlight, int twoTables) {
        return driver.findElement(By.xpath("/html/body/form[6]/div[1]/div/div[1]/div[2]/div/div[1]/div[3]/div/div[" + twoTables +
                "]/div[1]/div/div/table/tbody/tr[2]/td/div/table/tbody/tr[" + rowStart+ "]/td[" + howManyFlight + "]/span[3]")).getText();
    }

    private String getFlightFrom(int rowStart, int howManyFlight, int twoTables) {
        return driver.findElement(By.xpath("/html/body/form[6]/div[1]/div/div[1]/div[2]/div/div[1]/div[3]/div/div[" + twoTables +
                "]/div[1]/div/div/table/tbody/tr[2]/td/div/table/tbody/tr[" + rowStart +"]/td[" + howManyFlight + "]/span[1]")).getText();
    }

    private String getCheapestPrice(int rowNumber, int twoTables) {
        return driver.findElement(By.xpath("/html/body/form[6]/div[1]/div/div[1]/div[2]/div/div[1]/div[3]/div/div[" +  twoTables +
                "]/div[1]/div/div/table/tbody/tr[" + rowNumber+"]/td[1]/div/span[2]/span[2]")).getText();
    }

    private boolean chooseFlight(int rowNumber, int twoTables) {
        boolean rowExist;

        try{driver.findElement(By.xpath("/html/body/form[6]/div[1]/div/div[1]/div[2]/div/div[1]/div[3]/div/div[" + twoTables +
                "]/div[1]/div/div/table/tbody/tr[" + rowNumber+"]/td[1]/div/span[2]/span[2]")).click();
            rowExist = true;
        }catch (Exception e){
            rowExist = false;
        }
        return rowExist;
    }

    private void searchButtonClick() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_" +
                "CEPGroup1_CEPActive_cepNDPRevBookingArea_Searchbtn_ButtonLink")).click();
    }

    private void insertFlightDateTo() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[1]/form/div[4]/div[2]/div[4]/div[2]/div[1]/div[1]/div[2]/div[6]" +
                "/div[2]/div[2]/input")).click();
        Thread.sleep(1247);
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[7]")).click();
    }

    private void insertFlightDateFrom() throws InterruptedException {
        Thread.sleep(2234);
        driver.findElement(By.xpath("/html/body/div[1]/form/div[4]/div[2]/div[4]/div[2]/div[1]/div[1]/div[2]/div[6]" +
                "/div[1]/div[2]/input[7]")).click();
        Thread.sleep(1234);
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
        Thread.sleep(2211);
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[1]/a")).click();
        Thread.sleep(1444);
    }

    private void insertFlightTo() throws InterruptedException {
        WebElement inputTo = driver.findElement(By.name("ctl00$FullRegion$MainRegion$ContentRegion$Content" +
                "FullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtTo"));
        inputTo.sendKeys("LHR");
        Thread.sleep(2210);
        driver.findElement(By.id("LHR")).click();
    }

    private void insertFlightFrom() throws InterruptedException {
        Thread.sleep(1190);
        WebElement inputFrom = driver.findElement(By.name("ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegi" +
                "on$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtFrom"));
        Thread.sleep(3190);
        inputFrom.sendKeys("ARN");
        Thread.sleep(3190);
        driver.findElement(By.id("ARN")).click();
        Thread.sleep(2801);
    }
}
