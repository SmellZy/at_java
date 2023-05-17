package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChromeSignInPage {
    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement username_field;
    @FindBy(xpath = "//*[@id=\"login-form\"]/fieldset/input[2]")
    private WebElement button;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement password_field;
    @FindBy(xpath = "//*[@id=\"login-form\"]/fieldset/input[3]")
    private WebElement loginButton;


    private WebDriver chromeDriver;
    public static final String URL = "http://localhost/login_page.php";
    public ChromeSignInPage(WebDriver chromeDriver){this.chromeDriver = chromeDriver;  chromeDriver.get(URL);}


    public ChromeSignInPage sendLogin(String login) {
        username_field.sendKeys(login);
        return this;
    }

    public ChromeSignInPage tapButton() {
        button.click();
        return this;
    }

    public ChromeSignInPage sendPassword(String password) {
        password_field.sendKeys(password);
        return this;
    }

    public ChromeSignInPage tapLoginButton() {
        loginButton.click();
        return this;
    }
}
