package itstep.course_tasks.task_12.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static itstep.course_tasks.task_12.WebDriverSupplier.driver;

public class LoginPage {
    //driver.findElement(By.xpath("//*[@id=\"loginusername\"]"))
    @FindBy(xpath = "//*[@id=\"loginusername\"]")
    private WebElement loginusername;
    //driver.findElement(By.xpath("//*[@id=\"loginpassword\"]"))
    @FindBy(xpath = "//*[@id=\"loginpassword\"]")
    private WebElement loginpassword;
    @FindBy(xpath = "//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")
    private WebElement loginbutton;
    @FindBy(xpath = "//*[@id=\"login2\"]")
    private WebElement goToLoginPage;
    @FindBy(xpath = "//*[@id=\"nameofuser\"]")
    private WebElement username;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public LoginPage inputLogin(String login) throws InterruptedException {
        loginusername.sendKeys(login);
        return this;
    }

    public LoginPage inputPassword(String password) throws InterruptedException {
        loginpassword.sendKeys(password);
        return this;
    }

    public LoginPage login() throws InterruptedException {
        loginbutton.click();
        return this;
    }

    public LoginPage goToLoginPage() throws InterruptedException {
        goToLoginPage.click();
        WebElement modalLogIn = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"logInModal\"]/div/div/div[2]"))));
        Assert.assertTrue(modalLogIn.isDisplayed());
        return new LoginPage();
    }

    public void isLoggedIn() throws InterruptedException {
        Thread.sleep(1000);
        username.isDisplayed();
    }
}
