package sel.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import properties.AppProperties;

public class WebDriverFactory {

    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    private static void setChromDriverProperty() {
        System.setProperty(WEBDRIVER_CHROME_DRIVER, AppProperties.WEB_DRIVER_LOCATION.getValue());
    }

    public WebDriver create() {
        setChromDriverProperty();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        return webDriver;
    }

}
