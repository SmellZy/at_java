package task_25_Framework.web_ui;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import itstep.task_25_Framework.Singleton.WebDriverSingleton;
import itstep.task_25_Framework.bo.WebUiChromeBO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebUiChromeTest {

    private WebDriver chromeDriver;
    //Test case: first user create issue, and assign second user. Second user logging in and close the issue assigned to him
    @BeforeTest
    void setUpChrome(){
        WebDriver chromeDriver = WebDriverSingleton.getChromeDriver();
        ChromeDriverManager.getInstance().setup();
        this.chromeDriver = chromeDriver;
    }

    @Test
    void chromeScenarioTest() throws InterruptedException {

        WebUiChromeBO webUiChromeBO = new WebUiChromeBO(chromeDriver);

        webUiChromeBO.signInUser().createIssue().sendIssue();
    }

    @AfterTest
    void tearDownChrome(){
        chromeDriver.close();
        chromeDriver.quit();
    }
}
