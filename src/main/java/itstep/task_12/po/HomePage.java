package itstep.task_12.po;

import itstep.task_12.Wrapper.Decorator;
import itstep.task_12.Wrapper.TextElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static itstep.task_12.WebDriverSupplier.driver;

public class HomePage {
    @FindBy(xpath = "//*[@id=\"signin2\"]")
    private WebElement signUpWindow;
    @FindBy(xpath = "//*[@id=\"login2\"]")
    private TextElement goToLoginPage;
    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[5]/div/a/img")
    private WebElement goToProductPage;



    public static final String URL = "https://www.demoblaze.com";
    public HomePage(){
        if (!URL.equals(driver.getCurrentUrl())){
            driver.get(URL);
            PageFactory.initElements(new Decorator(driver), this);
        }
    }

    public SingUpPage singUp() throws InterruptedException {
        signUpWindow.click();
        return new SingUpPage();
    }

    public LoginPage goToLoginPage() throws InterruptedException {
        goToLoginPage.waitForVisibleClick();
        WebElement modalLogIn = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"logInModal\"]/div/div/div[2]"))));
        Assert.assertTrue(modalLogIn.isDisplayed());
        return new LoginPage();
    }

    public ProductPage goToProduct() throws InterruptedException {
        Thread.sleep(700);
        goToProductPage.click();
        return new ProductPage();
    }

    public TextElement verifyDiv(){
        Boolean result = goToLoginPage.verifyDivExists(By.className("modal-body"));
        System.out.println(result);
        return verifyDiv();
    }

}
