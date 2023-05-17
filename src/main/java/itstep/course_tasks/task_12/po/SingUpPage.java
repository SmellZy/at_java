package itstep.course_tasks.task_12.po;

import itstep.course_tasks.task_12.WebDriverSupplier;
import itstep.course_tasks.task_12.Wrapper.TextElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SingUpPage {

    @FindBy(xpath = "//*[@id=\"signInModal\"]/div/div/div[2]")
    private TextElement signUpWindow;
    @FindBy(xpath = "//*[@id=\"sign-username\"]")
    private WebElement inputLogin;
    @FindBy(xpath = "//*[@id=\"sign-password\"]")
    private WebElement inputPassword;
    @FindBy(xpath = "//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")
    private WebElement signUpButton;

    public SingUpPage() {
        PageFactory.initElements(WebDriverSupplier.driver, this);
    }


    public TextElement idOpen() throws InterruptedException {
        signUpWindow.waitForDivToBeVisible(By.xpath("//*[@id=\"signInModal\"]/div/div"));
        System.out.println("div id already displayed: " + signUpWindow.verifyDivExists(By.xpath("//*[@id=\"signInModal\"]/div/div")));
        return idOpen();
    }

    public SingUpPage inputLogin(String login) throws InterruptedException {
        inputLogin.sendKeys(login);
        return this;
    }

    public SingUpPage inputPassword(String password) throws InterruptedException {
        inputPassword.sendKeys(password);
        return this;
    }

    public SingUpPage singUp() throws InterruptedException {
        signUpButton.click();
        return this;
    }

    public HomePage checkAndAcceptAllert() throws InterruptedException {
        //1.3 click on modal window OK
        Thread.sleep(700);
        Assert.assertEquals(WebDriverSupplier.driver.switchTo().alert().getText(), "Sign up successful.", "unexpected message");
        //1.4 click on modal window OK
        WebDriverSupplier.driver.switchTo().alert().accept();
        return new HomePage();
    }
}
