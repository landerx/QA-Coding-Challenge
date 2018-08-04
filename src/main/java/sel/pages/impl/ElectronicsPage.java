package sel.pages.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sel.pages.Page;
import sel.waits.WaitForElement;

@Slf4j
public class ElectronicsPage extends Page {

    @FindBy(xpath = "//*[@data-box-name='Nagłówek kategorii Komputery i tablety']//a[@href='/kategoria/komputery?order=m']")
    private WebElement computersLink;

    public ElectronicsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ComputersPage clickOnComputersLink() {
        log.info("Click on computers link");
        WaitForElement.waitUntilElementIsClickable(webDriver, computersLink);
        computersLink.click();
        return new ComputersPage(webDriver);
    }
}
