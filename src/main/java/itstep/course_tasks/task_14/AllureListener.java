package itstep.course_tasks.task_14;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static itstep.course_tasks.task_12.WebDriverSupplier.driver;

public class AllureListener implements ITestListener {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        LOGGER.info(result.getName() + "onTestStart");
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        LOGGER.info(context.getName() + "onStart");
//        makeScreenshot();
    }

    @Attachment(value = "Page Screen", type = "image/png")
    private byte[] makeScreenshot(){
        LOGGER.error("makeScreenshot");
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

        //Error: Cannot install in Homebrew on ARM processor :(
    }
}
