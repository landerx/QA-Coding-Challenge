package sel.waits;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

@Slf4j
public class WaitForElement {

    private static WebDriverWait getWebDriverWait(WebDriver webDriver) {
        return new WebDriverWait(webDriver, 60);
    }

    public static void waitUntilElementIsVisible(WebDriver webDriver, WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait(webDriver);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsClickable(WebDriver webDriver, WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait(webDriver);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitUntilSelectOptionsPopulated(WebDriver webDriver, final Select select) {
        return new FluentWait<>(webDriver)
                .pollingEvery(Duration.ofMillis(250))
                .withTimeout(Duration.ofSeconds(5))
                .until(webDriver1 -> Objects.nonNull(select.getOptions()) && select.getOptions().size() > 1);
    }

    public static void waitUntilElementClassAttributeIsEmpty(WebDriver webDriver, WebElement webElement) {
        WaitForElement.waitUntilElementIsVisible(webDriver, webElement);
        getWebDriverWait(webDriver)
                .until(webDriver1 -> {
                    boolean isEmptyClassAttribute = doesWebElementHasEmptyClass(webElement);
                    log.info("Does web element class attribute is empty? " + isEmptyClassAttribute);
                    return isEmptyClassAttribute;
                });
    }

    private static boolean doesWebElementHasEmptyClass(WebElement webElement) {
        return webElement.getAttribute("class").equals("");
    }

}