package sel.waits;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static junit.framework.TestCase.fail;

@Slf4j
public class LoadPageWait {

    private static final int SCRIPT_TIME_OUT_VALUE = 60;
    private static final String RETURN_DOCUMENT_READY_STATE = "return document.readyState";

    public static void waitForPageToLoad(WebDriver webDriver) {
        ExpectedCondition<Boolean> javascriptDocumentLoaded = checkIfJavaScriptDocumentWasLoaded();
        Wait<WebDriver> wait = new WebDriverWait(webDriver, SCRIPT_TIME_OUT_VALUE);
        try {
            wait.until(javascriptDocumentLoaded);
        } catch (Throwable error) {
            fail("There was an error while waiting for page: " + error.toString());
        }
    }

    private static ExpectedCondition<Boolean> checkIfJavaScriptDocumentWasLoaded() {
        return driver -> {
            Object documentReadyState = ((JavascriptExecutor) Objects.requireNonNull(driver))
                    .executeScript(RETURN_DOCUMENT_READY_STATE);
            return documentReadyState.equals("complete");
        };
    }

}
