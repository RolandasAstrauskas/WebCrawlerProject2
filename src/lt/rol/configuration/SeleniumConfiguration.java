package lt.rol.configuration;



import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


public class SeleniumConfiguration {

    final private String URL = "https://classic.flysas.com/en/de/";
    final private String DRIVER = "webdriver.chrome.driver";
    final private String DRIVER_PATH = "C:\\Users\\37068\\Desktop\\Selenium2\\chromedriver.exe";



    public ChromeDriver getDriver() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        options.addArguments("--enable-javascript");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--lang=en-US,en;q=0.9,lt;q=0.8");
        options.addArguments("--disable-application-cache");
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.acceptInsecureCerts();
        options.merge(desiredCapabilities);
        System.setProperty(DRIVER,DRIVER_PATH);

        ChromeDriver driver = new ChromeDriver(options);
        driver.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get(URL);

        return driver;
    }
}









































