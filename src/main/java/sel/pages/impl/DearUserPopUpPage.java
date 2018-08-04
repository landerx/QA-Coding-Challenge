package sel.pages.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sel.pages.Page;
import sel.waits.WaitForElement;

@Slf4j
public class DearUserPopUpPage extends Page {

    @FindBy(css = "button[data-analytics-interaction-value='closeIcon']")
    private WebElement closePopUpButton;

    public DearUserPopUpPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage clickOnXButton() {
        log.info("Click on x button to accept consent");
        WaitForElement.waitUntilElementIsClickable(webDriver, closePopUpButton);
        closePopUpButton.click();
        return new HomePage(webDriver);
    }

}
