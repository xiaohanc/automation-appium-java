package vice.tests;

import driver.PageFactory;
import driver.ViceAssert;
import driver.ViceReporter;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
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

    @AfterClass(description = "Stopping Appium Server and Freeing Device")
    public static void tearDownClass() {
        Appium.killServer(appiumProcess);
        Driver.freeUDID(UDID);
    }

    @Test(description = "Test Name, Test 1, Class 1")
    @Description("Description for Test 1, this test will fail")
    public void testOne() {
        PageFactory.WelcomeOverlay().waitForViewToLoad(driver);
        PageFactory.WelcomeOverlay().skipButton.click();

        PageFactory.ReadSectionView().waitForViewToLoad(driver);
        PageFactory.ReadSectionView().readButton.click();

        PageFactory.ReadSectionView().popularTab.click();

        ViceReporter.Step("Starting Assertions");

        ViceAssert.assertEquals(PageFactory.ReadSectionView().popularTab.getText(), "SOMETHING ELSE");
        ViceAssert.assertEquals(PageFactory.ReadSectionView().latestTab.getText(), "LATEST");

    }

    @Test(description = "Test Name, Test 2, Class 1")
    @Description("Description for Test 2, this test will pass")
    public void testTwo() {
        PageFactory.WelcomeOverlay().waitForViewToLoad(driver);
        PageFactory.WelcomeOverlay().skipButton.click();
        PageFactory.ReadSectionView().waitForViewToLoad(driver);
        PageFactory.ReadSectionView().readButton.click();

        PageFactory.ReadSectionView().popularTab.click();

        ViceReporter.Step("Starting Assertions");

        ViceAssert.assertEquals(PageFactory.ReadSectionView().popularTab.getText(), "POPULAR");
        ViceAssert.assertEquals(PageFactory.ReadSectionView().latestTab.getText(), "LATEST");
    }

}