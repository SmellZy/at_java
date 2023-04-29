package task_11;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class EndToEndTest {

    private WebDriver driver;
    @BeforeTest
    void setup(){
        //Setup ChromeDriver using driver file and properties.
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        //WebDriver driver = new ChromeDriver();


        //Setup ChromeDriver using DriverManager.
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }



    @Test
    void endToEndTest() throws InterruptedException {

        // target https://www.demoblaze.com

        driver.get("https://www.demoblaze.com");

        driver.findElement(By.xpath("//*[@id=\"signin2\"]")).click();

        WebElement modalSingUp = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"signInModal\"]/div/div/div[2]"))));
        Assert.assertTrue(modalSingUp.isDisplayed());
//        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[2]")).isDisplayed());

        //1.2 sing up credentials
        String login = randomString();
        String password = randomString();
        String name = randomString();
        String country = randomString();
        String city  = randomString();
        String creditCard = randomString();
        String month = randomString();
        String year = randomString();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"sign-username\"]")).sendKeys(login);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"sign-password\"]")).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        //1.3 click on modal window OK
        Assert.assertEquals(driver.switchTo().alert().getText(), "Sign up successful.", "unexpected message");

        Thread.sleep(1000);

        //1.4 click on modal window OK
         driver.switchTo().alert().accept();

        Thread.sleep(1000);

        //2 login
        driver.findElement(By.xpath("//*[@id=\"login2\"]")).click();
        Thread.sleep(1000);
        WebElement modalLogIn = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"logInModal\"]/div/div/div[2]"))));
        Assert.assertTrue(modalLogIn.isDisplayed());
        Thread.sleep(1000);

        //2.1 input credentials
        driver.findElement(By.xpath("//*[@id=\"loginusername\"]")).sendKeys(login);
        driver.findElement(By.xpath("//*[@id=\"loginpassword\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();

        Thread.sleep(3000);

        //2.2 check if we logged in corectly and display our username
        driver.findElement(By.xpath("//*[@id=\"nameofuser\"]")).isDisplayed();

        //2.3 find product to buy and redirect to him
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[5]/div/a/img")).click();

        Thread.sleep(3000);

        //2.4 add to cart
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();

        Thread.sleep(1300);

        //2.5 accept allert window
        Assert.assertEquals(driver.switchTo().alert().getText(), "Product added.", "unexpected message");

        driver.switchTo().alert().accept();

        Thread.sleep(1000);
        //
        driver.findElement(By.xpath("//*[@id=\"cartur\"]")).click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr[1]/td[2]")).isDisplayed());
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id=\"country\"]")).sendKeys(country);
        driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys(city);
        driver.findElement(By.xpath("//*[@id=\"card\"]")).sendKeys(creditCard);
        driver.findElement(By.xpath("//*[@id=\"month\"]")).sendKeys(month);
        driver.findElement(By.xpath("//*[@id=\"year\"]")).sendKeys(year);
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(200);
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[10]")).isDisplayed());

        driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click();

    }

    private String randomString() {
        return UUID.randomUUID().toString().substring(0, 10).replace("-", "");
    }

    @AfterTest
    void shutDown(){
        driver.close();
        driver.quit();
    }
}
