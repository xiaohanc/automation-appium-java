package driver;

import io.appium.java_client.events.api.general.ElementEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementListener implements ElementEventListener {
    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        ViceReporter.log(Action.BeforeClick, webElement, webDriver);
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        ViceReporter.log(Action.AfterClick, webElement, webDriver);
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }
}
