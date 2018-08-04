package sel.pages.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sel.pages.Page;
import sel.waits.WaitForElement;

@Slf4j
public class CategoriesPage extends Page {

    @FindBy(css = "[data-group-id='header-layers-desktop_Elektronika'] > a")
    private WebElement electronicsLink;

    public CategoriesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ElectronicsPage clickOnElectronicsLink() {
        log.info("Click on electronics link");
        WaitForElement.waitUntilElementIsClickable(webDriver, electronicsLink);
        electronicsLink.click();
        return new ElectronicsPage(webDriver);
    }

}
