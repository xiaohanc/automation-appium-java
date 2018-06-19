package driver;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class ViceReporter {

    public static void log(Action action, WebElement element, WebDriver webDriver) {
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            String elementLocator = element.toString().split(" -> ")[1].replace("]", "");

            customStep(action.getStringValue(), elementLocator, scrFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("{0}")
    public static void Step(String step) {
    }

    @Step("{0} on {1}")
    private static void customStep(String action, String element, File screenshot) throws IOException {
        Allure.addAttachment(element, Files.newInputStream(screenshot.toPath()));
    }

}
