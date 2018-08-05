package sel.factories;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.util.Objects;

@Slf4j
public class WebDriverFactory {

    private static final String WEB_DRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_RESOURCE_PATH ="drivers/chromedriver.exe";

    private static void setChromeDriverProperty(String webDriverPath) {
        log.info("Web driver is stored in: " + webDriverPath);
        System.setProperty(WEB_DRIVER_CHROME_DRIVER,webDriverPath);
    }

    public WebDriver create() {
        log.info("Creating web driver");
        setChromeDriverProperty(getWebDriverPath());
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        return webDriver;
    }

    private String getWebDriverPath() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(WEB_DRIVER_RESOURCE_PATH);
        if(Objects.isNull(resource)){
            throw new RuntimeException("Web driver isn't found in " + WEB_DRIVER_RESOURCE_PATH);
        }
        return resource.getPath();
    }

}
