package task_25_Framework.web_ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebUiTest {

    //Test case: first user create issue, and assign second user. Second user logging in and


    @Test
    void chromeScenarioTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "AT_Java/driver/chromedriver");
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver chromeDriver = new ChromeDriver(options);
        //step 1 get the localhost mantis
        chromeDriver.get("http://localhost/login_page.php");
        //step 2 tap login button     admin details: e0db5b05-4	root
        chromeDriver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("e0db5b05-4");
        chromeDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]")).click();
        //step 3 send password and tap login button
        chromeDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("root");
        chromeDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]")).click();
        //step 4 find and click "create issue"
        chromeDriver.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[3]/a")).click();
        //step 5 add some details to issue
        chromeDriver.findElement(By.xpath("//*[@id=\"reproducibility\"]")).click();
        WebElement waitMenu = new WebDriverWait(chromeDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reproducibility\"]/option[2]")));
        waitMenu.click();
        //add відтворюваність: іноді
        chromeDriver.findElement(By.xpath("//*[@id=\"reproducibility\"]/option[2]")).click();
        //кому призначити
        chromeDriver.findElement(By.xpath("//*[@id=\"handler_id\"]")).click();
        //assign user: 1b3c530f-b
        WebElement chooseUser = new WebDriverWait(chromeDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"handler_id\"]/option[2]")));
        waitMenu.click();
        chromeDriver.findElement(By.xpath("//*[@id=\"handler_id\"]/option[2]")).click();
        //some statistic
        chromeDriver.findElement(By.xpath("//*[@id=\"summary\"]")).sendKeys("some statistic");
        //some description
        chromeDriver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("some description");
        //step 6 send issue
        chromeDriver.findElement(By.xpath("//*[@id=\"report_bug_form\"]/div/div[2]/div[2]/input")).click();
        chromeDriver.quit();

    }

    @Test
    void firefoxScenarioTest(){
        System.setProperty("webdriver.gecko.driver","/Users/dima/Development/AT_Java/driver/geckodriver");
        FirefoxDriverManager.getInstance().setup();
        WebDriver firefoxDriver = new FirefoxDriver();

        //step 1 get the localhost mantis
        firefoxDriver.get("http://localhost/login_page.php");
        //step 2 tap login button     1b3c530f-b root
        firefoxDriver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("1b3c530f-b");
        firefoxDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]")).click();
        //step 3 send password and tap login button
        firefoxDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("root");
        firefoxDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]")).click();
        //step 4 click on issue assigned to me
        firefoxDriver.findElement(By.xpath("//*[@id=\"assigned\"]/div[2]/div/div/table/tbody/tr/td[2]/span[1]/a")).click();
        //step 5 click on "close issue button"
        firefoxDriver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tfoot/tr/td/div/div[6]/form/fieldset/input[5]")).click();
        //step 5.1 type some information
        firefoxDriver.findElement(By.xpath("//*[@id=\"bugnote_text\"]")).sendKeys("some comment");
        //step 6 close issue
        firefoxDriver.findElement(By.xpath("//*[@id=\"bug-change-status-form\"]/fieldset/div/div[2]/div[2]/input")).click();
        //step 7 assert issue is closed?
        String issueText = firefoxDriver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[7]/td[2]")).getText();
        Assert.assertEquals(issueText, "решена");
        firefoxDriver.quit();
    }
}
