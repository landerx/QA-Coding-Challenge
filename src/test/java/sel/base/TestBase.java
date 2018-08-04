package sel.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import sel.factories.WebDriverFactory;

@Slf4j
public class TestBase {

    protected WebDriver webDriver;
    private WebDriverFactory webDriverFactory;

    public TestBase() {
        this.webDriverFactory = new WebDriverFactory();
    }

    @Before
    public void setupWebDriver() {
        webDriver = webDriverFactory.create();
    }

    @After
    public void quitWebDriver() {
        log.info("Quiting web driver");
        webDriver.quit();
    }
}
