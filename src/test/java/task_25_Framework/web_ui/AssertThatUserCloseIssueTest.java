package task_25_Framework.web_ui;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import itstep.task_25_Framework.singleton.WebDriverSingleton;
import itstep.task_25_Framework.bo.AssertUserCloseIssueBO;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AssertThatUserCloseIssueTest {

    private WebDriver chromeDriver;
    //Test case: first user create issue, and assign second user. Second user logging in and close the issue assigned to him
    @BeforeTest
    void setUpChrome(){
        WebDriver chromeDriver = WebDriverSingleton.getChromeDriver();
        ChromeDriverManager.getInstance().setup();
        this.chromeDriver = chromeDriver;
    }

    @Test
    void assertionTestScenario() throws InterruptedException {

        AssertUserCloseIssueBO assertUserCloseIssueBO = new AssertUserCloseIssueBO(chromeDriver);

        assertUserCloseIssueBO.singIn().checkIssueClosed();
    }

    @AfterTest
    void tearDownChrome(){
        chromeDriver.close();
        chromeDriver.quit();
    }
}
