package vice.tests;

import com.qa.vice.AppiumProcess;
import driver.PageFactory;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import test.BaseTest;
import utils.Appium;
import utils.Driver;

public class Sample2Test extends BaseTest {
    public static AppiumProcess appiumProcess;
    public static String UDID;

    @AfterClass(description = "Stopping Appium Server and Freeing Device")
    public static void tearDownClass() {
        Appium.killServer(appiumProcess);
        Driver.freeUDID(UDID);
    }

    @Test(description = "Test Name, Test 1, Class 2")
    @Description("Description for Test 1, this test will pass")
    public void testOne() {
        //->  WelcomeOverlay welcomeOverlay = getInstance(PageFactory.ReadSectionView.class);

        PageFactory.WelcomeOverlay().waitForViewToLoad(driver);
        PageFactory.WelcomeOverlay().skipButton.click();

        PageFactory.ReadSectionView().waitForViewToLoad(driver);
        PageFactory.ReadSectionView().readButton.click();

        PageFactory.ReadSectionView().popularTab.click();
        Assert.assertEquals(PageFactory.ReadSectionView().popularTab.getText(), "POPULAR");
        Assert.assertEquals(PageFactory.ReadSectionView().latestTab.getText(), "LATEST");
    }

    @Test(description = "Test Name, Test 12, Class 2")
    @Description("Description for Test 2, this test will fail")
    public void testTwo() {

        PageFactory.WelcomeOverlay().waitForViewToLoad(driver);
        PageFactory.WelcomeOverlay().skipButton.click();

        PageFactory.ReadSectionView().waitForViewToLoad(driver);
        PageFactory.ReadSectionView().readButton.click();

        PageFactory.ReadSectionView().popularTab.click();

        Assert.assertEquals(PageFactory.ReadSectionView().popularTab.getText(), "POPULAR");
        Assert.assertEquals(PageFactory.ReadSectionView().latestTab.getText(), "MENTIRA");
    }


}