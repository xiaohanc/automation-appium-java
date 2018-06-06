import driver.PageFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import test.BaseTest;
import utils.Appium;
import utils.Driver;

/**
 * TestClass must inherit from BaseTest
 * and must implement @AfterClass
 */
public class SampleTest extends BaseTest {
    public static com.qa.vice.AppiumProcess appiumProcess;
    public static String UDID;

    /**
     * tearDownClass is invoked after all test from this TestClass has been executed,
     * here, we kill the created appium process and frees the android device
     */
    @AfterClass
    public static void tearDownClass() {
        Appium.killServer(appiumProcess);
        Driver.freeUDID(UDID);
    }

    @Test
    public void testOne() {
        System.out.println("starting SampleTest testOne");

        PageFactory.WelcomeOverlay().waitForViewToLoad(driver);
        PageFactory.WelcomeOverlay().skipButton.click();

        PageFactory.ReadSectionView().waitForViewToLoad(driver);
        PageFactory.ReadSectionView().readButton.click();

        PageFactory.ReadSectionView().popularTab.click();

        Assert.assertEquals(PageFactory.ReadSectionView().popularTab.getText(), "MENTIRA");
        Assert.assertEquals(PageFactory.ReadSectionView().latestTab.getText(), "LATEST");
        System.out.println("finishing SampleTest testOne");

    }

    @Test
    public void testTwo() {
        System.out.println("starting SampleTest testTwo");
        PageFactory.WelcomeOverlay().waitForViewToLoad(driver);
        PageFactory.WelcomeOverlay().skipButton.click();
        PageFactory.ReadSectionView().waitForViewToLoad(driver);
        PageFactory.ReadSectionView().readButton.click();

        PageFactory.ReadSectionView().popularTab.click();

        Assert.assertEquals(PageFactory.ReadSectionView().popularTab.getText(), "POPULAR");
        Assert.assertEquals(PageFactory.ReadSectionView().latestTab.getText(), "LATEST");
        System.out.println("finishing SampleTest testTwo");
    }


}