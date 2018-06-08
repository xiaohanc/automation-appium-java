package test;

import com.qa.vice.AppiumProcess;
import driver.ElementListener;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import utils.Appium;
import utils.Driver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * setUp is invoked before each TestMethod, here we make sure to have an Appium Process and a Driver instance running
     * before running the test itself, using reflection, we verify if the TestClass has an UDID assigned, if not, it waits and gets
     * for the next UDID to be available, once it has the UDID, is check if the Appium Process for this TestClass is created,
     * if not, creates one.
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @BeforeMethod(alwaysRun = true, description = "Setting Up Test")
    public void setUp() throws Exception {
        Field field = Class.forName(this.getClass().getName()).getDeclaredField("UDID");
        field.setAccessible(true);
        final Object UDIDValue = field.get(Class.forName(this.getClass().getName()));
        String UDID = (String) UDIDValue;
        if (UDIDValue == null) {
            UDID = Driver.getNextAvailableUDID();
            field.set(null, UDID);
        }

        field = Class.forName(this.getClass().getName()).getDeclaredField("appiumProcess");
        field.setAccessible(true);
        final Object appiumProcessValue = field.get(Class.forName(this.getClass().getName()));
        AppiumProcess appiumProcess = (AppiumProcess) appiumProcessValue;
        if (appiumProcess == null) {
            appiumProcess = Appium.startServer();
            field.set(null, appiumProcess);
        }

        System.out.println("Creating new Driver connection to Appium on Port " + appiumProcess.port + " with UDID " + UDID);
        AndroidDriver androidDriver = new AndroidDriver(new URL(String.format("http://127.0.0.1:%s/wd/hub", appiumProcess.port)), Driver.getCapabilities(UDID));
        this.driver = EventFiringWebDriverFactory.getEventFiringWebDriver(androidDriver, new ElementListener());

    }

    /**
     * tearDown is invoked after each TestMethod, here we quit the driver.
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @AfterMethod(alwaysRun = true, description = "Cleaning Up Test")
    public void tearDown() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        if (driver != null) {
            Field field = Class.forName(this.getClass().getName()).getDeclaredField("appiumProcess");
            field.setAccessible(true);
            final Object appiumProcessValue = field.get(Class.forName(this.getClass().getName()));
            AppiumProcess appiumProcess = (AppiumProcess) appiumProcessValue;

            field = Class.forName(this.getClass().getName()).getDeclaredField("UDID");
            field.setAccessible(true);
            final Object UDIDValue = field.get(Class.forName(this.getClass().getName()));
            String UDID = (String) UDIDValue;
            System.out.println("Closing  Driver connection on Port " + appiumProcess.port + " with UDID " + UDID);
            driver.quit();
            driver = null;
        }
    }

}
