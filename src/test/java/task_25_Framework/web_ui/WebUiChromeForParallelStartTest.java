package task_25_Framework.web_ui;

import itstep.task_25_Framework.bo.WebUiChromeBO;
import itstep.task_25_Framework.bo.WebUiFirefoxBO;
import itstep.task_25_Framework.parallel_driver_pool.ParallelDriverPool;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebUiChromeForParallelStartTest {

    private WebDriver chromeDriver;
    private WebDriver firefoxDriver;

    @BeforeTest
    void setUpParallel() {
        Thread chromeThread = new Thread(() -> {
            chromeDriver = ParallelDriverPool.getChromeDriver();
            // Use the Chrome WebDriver instance
        });

        Thread firefoxThread = new Thread(() -> {
            firefoxDriver = ParallelDriverPool.getFirefoxDriver();
            // Use the Firefox WebDriver instance
        });

        chromeThread.start();
        firefoxThread.start();

        try {
            chromeThread.join();
            firefoxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void chromeScenarioTest() throws InterruptedException {
        WebUiChromeBO webUiChromeBO = new WebUiChromeBO(chromeDriver);
        WebUiFirefoxBO webUiFirefoxBO = new WebUiFirefoxBO(firefoxDriver);

        webUiChromeBO.signInUser().createIssue().sendIssue();
        webUiFirefoxBO.signIn().getIssue().closeIssue().assertIssueIsClosed();
    }

    @AfterTest
    void tearDownChrome() {
        chromeDriver.quit();
        firefoxDriver.quit();
    }
}
