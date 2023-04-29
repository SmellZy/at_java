package itstep.Task_11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public LoginPage inputLogin(String login) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"loginusername\"]")).sendKeys(login);
        return this;
    }

    public LoginPage inputPassword(String password) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"loginpassword\"]")).sendKeys(password);
        return this;
    }

    public LoginPage login() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
        return this;
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

    public void isLoggedIn() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"nameofuser\"]")).isDisplayed();
    }
}
