package task_25_Framework.web_ui;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import itstep.task_25_Framework.Singleton.WebDriverSingleton;
import itstep.task_25_Framework.bo.WebUiChromeBO;
import itstep.task_25_Framework.bo.WebUiFirefoxBO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebUiFirefoxTest {
    private WebDriver firefoxDriver;
    //Test case: first user create issue, and assign second user. Second user logging in and close the issue assigned to him

    @BeforeTest
    void setUpFirefox(){
        WebDriver firefoxDriver = WebDriverSingleton.getFirefoxDriver();
        FirefoxDriverManager.getInstance().setup();
        this.firefoxDriver = firefoxDriver;
    }


    @Test
    void firefoxScenarioTest(){

        WebUiFirefoxBO webUiFirefoxBO = new WebUiFirefoxBO(firefoxDriver);

        webUiFirefoxBO.signIn().getIssue().closeIssue().assertIssueIsClosed();

    }

    @AfterTest
    void tearDownFirefox(){
        firefoxDriver.quit();
    }
}
