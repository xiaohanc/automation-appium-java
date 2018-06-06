import driver.PageFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import test.BaseTest;
import utils.Appium;
import utils.Driver;

public class Sample4Test extends BaseTest {
    public static com.qa.vice.AppiumProcess appiumProcess;
    public static String UDID;

    @AfterClass
    public static void tearDownClass() {
        Appium.killServer(appiumProcess);
        Driver.freeUDID(UDID);
    }

    @Test
    public void testOne() {
        System.out.println("starting Sample4Test testOne");
        PageFactory.WelcomeOverlay().waitForViewToLoad(driver); //
        PageFactory.WelcomeOverlay().skipButton.click();
        PageFactory.ReadSectionView().waitForViewToLoad(driver);
        PageFactory.ReadSectionView().readButton.click();

        PageFactory.ReadSectionView().popularTab.click();

        Assert.assertEquals(PageFactory.ReadSectionView().popularTab.getText(), "POPULAR");
        Assert.assertEquals(PageFactory.ReadSectionView().latestTab.getText(), "LATEST");

        System.out.println("finishing Sample4Test testOne");

    }

    @Test
    public void testTwo() {
        System.out.println("starting Sample4Test testTwo");
        PageFactory.WelcomeOverlay().waitForViewToLoad(driver);
        PageFactory.WelcomeOverlay().skipButton.click();
        PageFactory.ReadSectionView().waitForViewToLoad(driver);
        PageFactory.ReadSectionView().readButton.click();

        PageFactory.ReadSectionView().popularTab.click();

        Assert.assertEquals(PageFactory.ReadSectionView().popularTab.getText(), "POPULAR");
        Assert.assertEquals(PageFactory.ReadSectionView().latestTab.getText(), "LATEST");
        System.out.println("finishing Sample4Test testTwo");
    }


}