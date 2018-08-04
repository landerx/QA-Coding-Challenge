package sel.pages.impl;

import com.google.common.collect.Ordering;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import sel.pages.Page;
import sel.waits.WaitForElement;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.CombinableMatcher.both;

@Slf4j
public class ExternalAndPortableDiscsPage extends Page {

    private static final String EXTERNAL_AND_PORTABLE_DISCS_AND_TITLE = "Dyski zewnętrzne i przenośne";
    private static final String DESCENDING_BY_PRICE_OPTION = " cena: od najwyższej ";

    @FindBy(xpath = "//*[@class='_2e710a1'][1]//*[contains(@class,'_76bf772')]//*[@class='_745f8e9 _3e7ae09']//dd[2]")
    private List<WebElement> promotedOffersCapacities;

    @FindBy(xpath = "//*[@class='_2e710a1'][2]//*[contains(@class,'_76bf772')]//*[@class='_745f8e9 _3e7ae09']//dd[2]")
    private List<WebElement> commonOffersCapacities;

    @FindBy(xpath = "//*[@class='_2e710a1'][1]//*[contains(@class,'_76bf772')]//*[@class='_0d40a63']/*[@class='_7a9f73f']")
    private List<WebElement> promotedOffersPrice;

    @FindBy(xpath = "//*[@class='_2e710a1'][2]//*[contains(@class,'_76bf772')]//*[@class='_0d40a63']/*[@class='_7a9f73f']")
    private List<WebElement> commonOffersPrice;

    @FindBy(css = ".listing-title__category")
    private WebElement pageTitle;

    @FindBy(id = "pojemnosc-dysku-gb-od")
    private WebElement minGbCapacityInput;

    @FindBy(id = "pojemnosc-dysku-gb-do")
    private WebElement maxGbCapacityInput;

    @FindBy(css = "select[data-value='m']")
    private WebElement sortSelectWebElement;

    public ExternalAndPortableDiscsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ExternalAndPortableDiscsPage assertThatExternalAndPortableDiscsPageIsDisplayed() {
        log.info("Assert that external and portable discs page is displayed");
        WaitForElement.waitUntilElementIsVisible(webDriver, pageTitle);
        assertTrue(pageTitle.isDisplayed());
        assertThat(pageTitle.getText(), is(EXTERNAL_AND_PORTABLE_DISCS_AND_TITLE));
        return this;
    }

    public ExternalAndPortableDiscsPage typeIntoMinCapacity(Integer value) {
        log.info("Typing value {} into min gb capacity input", value);
        WaitForElement.waitUntilElementIsClickable(webDriver, minGbCapacityInput);
        minGbCapacityInput.sendKeys(String.valueOf(value));
        minGbCapacityInput.sendKeys(Keys.RETURN);
        return new ExternalAndPortableDiscsPage(webDriver);
    }

    public ExternalAndPortableDiscsPage typeIntoMaxCapacity(Integer value) {
        log.info("Typing value {} into max gb capacity input", value);
        WaitForElement.waitUntilElementIsClickable(webDriver, maxGbCapacityInput);
        maxGbCapacityInput.sendKeys(String.valueOf(value));
        maxGbCapacityInput.sendKeys(Keys.RETURN);
        return new ExternalAndPortableDiscsPage(webDriver);
    }

    public ExternalAndPortableDiscsPage pickSortDescendingByPriceFromSortDropdown() {
        log.info("Picking sort descending by price from sort dropdown");
        Select sortSelect = new Select(sortSelectWebElement);
        WaitForElement.waitUntilSelectOptionsPopulated(webDriver, sortSelect);
        sortSelect.selectByVisibleText(DESCENDING_BY_PRICE_OPTION);
        return this;
    }

    public ExternalAndPortableDiscsPage assertThatSortDescendingByPriceIsSelected() {
        log.info("Assert that sorting descend by price is selected");
        log.info(webDriver.getCurrentUrl());
        log.info(webDriver.getCurrentUrl());
        Select sortSelect = new Select(sortSelectWebElement);
        WaitForElement.waitUntilSelectOptionsPopulated(webDriver, sortSelect);
        assertThat(sortSelect.getFirstSelectedOption().getText(), is(DESCENDING_BY_PRICE_OPTION));
        return this;
    }

    public ExternalAndPortableDiscsPage assertThatPromotedOffersAreSortedDescendByPrice() {
        log.info("assert that promoted offers are sorted descend by price");
        assertThatOffersAreSortedDescendByPrice(promotedOffersPrice);
        return this;

    }

    public ExternalAndPortableDiscsPage assertThatCommonOffersAreSortedDescendByPrice() {
        log.info("assert that common offers are sorted descend by price");
        assertThatOffersAreSortedDescendByPrice(commonOffersPrice);
        return this;

    }

    private void assertThatOffersAreSortedDescendByPrice(List<WebElement> webElements) {
        log.info("assert that offers are sorted descend by price");
        List<Double> prices = webElements.stream()
                .map(webElement -> webElement.getText().replaceAll("[^\\d.]+", ""))
                .map(value -> Double.parseDouble(value) / 100.)
                .collect(Collectors.toList());
        assertThat(Ordering.natural().reverse().isOrdered(prices), is(true));
    }

    public ExternalAndPortableDiscsPage assertThatPromotedOffersAreBetweenGbValues(Integer min, Integer max) {
        log.info("assert that promoted offers are between {} and {} gb", min, max);
        assertThatOffersAreBetweenGbValues(promotedOffersCapacities, min, max);
        return this;

    }

    public ExternalAndPortableDiscsPage assertThatCommonOffersAreBetweenGbValues(Integer min, Integer max) {
        log.info("assert that common offers are between {} and {} gb", min, max);
        assertThatOffersAreBetweenGbValues(commonOffersCapacities, min, max);
        return this;

    }

    private void assertThatOffersAreBetweenGbValues(List<WebElement> webElements, Integer min, Integer max) {
        webElements.forEach(webElement -> {
            WaitForElement.waitUntilElementIsVisible(webDriver, webElement);
            if(webElement.getText().equals("320")){
                log.error("320");
            }
            assertThat(Integer.parseInt(webElement.getText()),
                    is(both(greaterThanOrEqualTo(min))
                            .and(lessThanOrEqualTo(max))));
        });
    }

}
