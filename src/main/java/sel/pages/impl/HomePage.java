package sel.pages.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sel.pages.Page;
import sel.waits.WaitForElement;


@Slf4j
public class HomePage extends Page {

    @FindBy(css = "*[data-description='header logo'] > a > img")
    private WebElement homeImage;

    @FindBy(css = "[data-dropdown-id ='categories_dropdown'] > a")
    private WebElement categoriesLink;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public CategoriesPage clickOnCategoriesLink() {
        log.info("click on categories label");
        WaitForElement.waitUntilElementIsClickable(webDriver, categoriesLink);
        categoriesLink.click();
        return new CategoriesPage(webDriver);
    }

}
