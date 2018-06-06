package PageObjects;

import driver.BasePageObject;
import driver.WaitsForOnViewLoad;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WelcomeOverlay extends BasePageObject {
    @AndroidFindBy(id = "com.vice.viceforandroid:id/textview_skip_btn")
    @WaitsForOnViewLoad
    public MobileElement skipButton;

}
