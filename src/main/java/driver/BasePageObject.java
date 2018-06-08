package driver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePageObject {

    public AndroidDriver driver;

    /**
     * waits for the view to load, looks for the mobileElement with the WaitsForOnViewLoad annotation and
     * waits for that specific element to be present on the view, also, sets the driver to this instance
     *
     * @param driver
     */
    public void waitForViewToLoad(AndroidDriver driver) {
        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(this.driver, Duration.ofSeconds(30)), this);

        for (Field declaredField : this.getClass().getDeclaredFields()) {
            WaitsForOnViewLoad waitsFor = declaredField.getAnnotation(WaitsForOnViewLoad.class);

            if (waitsFor != null) {
                try {
                    declaredField.setAccessible(true);
                    Object toWaitForOjb = declaredField.get(this);
                    if (toWaitForOjb instanceof WebElement) {
                        WebElement toWaitFor = (WebElement) toWaitForOjb;
                        WebDriverWait wait = new WebDriverWait(driver, 30);
                        wait.until(ExpectedConditions.visibilityOf(toWaitFor));
                    }
                } catch (IllegalAccessException ex) {
                }
            }
        }
    }

}
