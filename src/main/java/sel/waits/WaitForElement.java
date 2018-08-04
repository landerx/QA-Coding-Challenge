package sel.waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class WaitForElement {

    private static WebDriverWait getWebDriverWait(WebDriver webDriver) {
        return new WebDriverWait(webDriver, 10);
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

}