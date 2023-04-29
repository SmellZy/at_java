package itstep.Task_11.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SingUpPage {
    private WebDriver driver;
    public SingUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean idOpen() throws InterruptedException {
        Thread.sleep(1000);
        return driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[2]")).isDisplayed();
    }

    public SingUpPage inputLogin(String login) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"sign-username\"]")).sendKeys(login);
        return this;
    }

    public SingUpPage inputPassword(String password) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"sign-password\"]")).sendKeys(password);
        return this;
    }

    public SingUpPage singUp() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
        return this;
    }

    public HomePage checkAndAcceptAllert() throws InterruptedException {
        Thread.sleep(1000);
        //1.3 click on modal window OK
        Assert.assertEquals(driver.switchTo().alert().getText(), "Sign up successful.", "unexpected message");
        Thread.sleep(1000);
        //1.4 click on modal window OK
        driver.switchTo().alert().accept();
        Thread.sleep(1000);

        return new HomePage(driver);
    }
}
