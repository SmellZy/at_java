package itstep.Task_11.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    public static final String URL = "https://www.demoblaze.com";
    public HomePage(WebDriver driver){
        this.driver = driver;
        if (!URL.equals(driver.getCurrentUrl())){
            driver.get(URL);
        }
    }

    public SingUpPage singUp() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"signin2\"]")).click();
        return new SingUpPage(driver);
    }

    public LoginPage goToLoginPage() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"login2\"]")).click();
        Thread.sleep(1000);
        WebElement modalLogIn = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"logInModal\"]/div/div/div[2]"))));
        Assert.assertTrue(modalLogIn.isDisplayed());
        Thread.sleep(1000);
        return new LoginPage(driver);
    }

    public ProductPage goToProduct() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[5]/div/a/img")).click();

        Thread.sleep(3000);
        return new ProductPage(driver);
    }
}
