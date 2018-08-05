package sel.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static sel.waits.LoadPageWait.waitForPageToLoad;

@Slf4j
public abstract class Page {

    protected WebDriver webDriver;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        waitForPageToLoad(webDriver);
        PageFactory.initElements(webDriver, this);
    }
}