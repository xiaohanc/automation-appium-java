package PageObjects;

import driver.BasePageObject;
import driver.WaitsForOnViewLoad;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ReadSectionView extends BasePageObject {
    @AndroidFindBy(id = "com.vice.viceforandroid:id/nav_read")
    @WaitsForOnViewLoad
    public MobileElement readButton;

    @AndroidFindBy(xpath = "//android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView")
    public MobileElement popularTab;

    @AndroidFindBy(xpath = "//android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView")
    public MobileElement latestTab;

}
