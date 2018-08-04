package sel.pages.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sel.pages.Page;
import sel.waits.WaitForElement;

@Slf4j
public class ComputersPage extends Page {

    @FindBy(css = "a[data-analytics-click-value='Dyski i pamięci przenośne']")
    private WebElement portableDiscsAndMemoryLink;

    public ComputersPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PortableDiscsAndMemoryPage clickOnPortableDiscsAndMemoryLink() {
        log.info("Click on portable discs and memory link");
        WaitForElement.waitUntilElementIsClickable(webDriver, portableDiscsAndMemoryLink);
        portableDiscsAndMemoryLink.click();
        return new PortableDiscsAndMemoryPage(webDriver);
    }
}
