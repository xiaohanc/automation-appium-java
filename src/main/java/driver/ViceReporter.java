package driver;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class ViceReporter {

    public static void log(Action action, WebElement element, WebDriver webDriver) {
        try {
            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            String verboseAction = action.getStringValue() + " on " + element.toString().split(" -> ")[1].replace("]", "");
            Allure.addAttachment(verboseAction, Files.newInputStream(scrFile.toPath()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
