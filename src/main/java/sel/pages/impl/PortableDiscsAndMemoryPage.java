package sel.pages.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sel.pages.Page;
import sel.waits.WaitForElement;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class PortableDiscsAndMemoryPage extends Page {

    private static final String PORTABLE_DISCS_AND_MEMORY_TITLE = "Dyski i pamięci przenośne";

    @FindBy(css = ".listing-title__category")
    private WebElement title;

    @FindBy(css = "a[data-analytics-click-value='Dyski zewnętrzne i przenośne']")
    private WebElement externalAndPortableDiscsLink;

    public PortableDiscsAndMemoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PortableDiscsAndMemoryPage assertThatPortableDiscsAndMemoryPageIsDisplayed() {
        log.info("Assert that portable discs and memory page is displayed");
        WaitForElement.waitUntilElementIsVisible(webDriver, title);
        assertTrue(title.isDisplayed());
        assertThat(title.getText(), is(PORTABLE_DISCS_AND_MEMORY_TITLE));
        return this;
    }

    public ExternalAndPortableDiscsPage clickOnExternalAndPortableDiscsLink() {
        log.info("Click on external and portable discs link");
        WaitForElement.waitUntilElementIsVisible(webDriver, externalAndPortableDiscsLink);
        externalAndPortableDiscsLink.click();
        return new ExternalAndPortableDiscsPage(webDriver);
    }

}
