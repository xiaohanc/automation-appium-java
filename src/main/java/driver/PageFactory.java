package driver;

import PageObjects.ReadSectionView;
import PageObjects.WelcomeOverlay;

/**
 * Every PageObject will be listed here as a method with its instance creation
 * this way we have a centralized Class to all PageObjects
 */
public class PageFactory extends PageObjectFactory {


    public static ReadSectionView ReadSectionView() {
        return getInstance(ReadSectionView.class);
    }

    public static WelcomeOverlay WelcomeOverlay() {
        return getInstance(WelcomeOverlay.class);
    }
}
