package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FirefoxSignInPage {
    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement usernameField;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@id=\"login-form\"]/fieldset/input[2]")
    private WebElement button;
    @FindBy(xpath = "//*[@id=\"login-form\"]/fieldset/input[3]")
    private WebElement loginButton;

    private WebDriver firefoxDriver;
    public static final String URL = "http://localhost/login_page.php";
    public FirefoxSignInPage(WebDriver firefoxDriver){PageFactory.initElements(this.firefoxDriver = firefoxDriver, this);  firefoxDriver.get(URL);}


    public FirefoxSignInPage sendLogin(String login) {
        usernameField.sendKeys(login);
        return this;
    }

    public FirefoxSignInPage tapButton() {
        button.click();
        return this;
    }

    public FirefoxSignInPage sendPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public FirefoxSignInPage tapLoginButton() {
        loginButton.click();
        return this;
    }
}
