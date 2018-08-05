package sel.factories;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import properties.AppProperties;

@Slf4j
public class WebDriverFactory {

    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    private static void setChromDriverProperty() {
        System.setProperty(WEBDRIVER_CHROME_DRIVER, AppProperties.WEB_DRIVER_LOCATION.getValue());
    }

    public WebDriver create() {
        log.info("Creating web driver");
        setChromDriverProperty();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        return webDriver;
    }

}
