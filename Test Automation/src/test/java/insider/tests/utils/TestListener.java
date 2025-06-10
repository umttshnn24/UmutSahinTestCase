package insider.tests.utils;

import insider.core.DriverManager;
import io.qameta.allure.Allure;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class TestListener extends TestWatcher {

    @Override
    protected void failed(Throwable e, Description description) {
        takeScreenshot(description.getMethodName());
    }

    private void takeScreenshot(String methodName) {
        WebDriver driver = DriverManager.getDriver();

        if (driver == null) {
            System.out.println("Driver is null. Screenshot cannot be taken.");
            return;
        }

        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(methodName + " - screenshot", new ByteArrayInputStream(screenshotBytes));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
