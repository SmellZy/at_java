package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChromeSignInPage {
    private WebDriver chromeDriver;
    public static final String URL = "http://localhost/login_page.php";
    public ChromeSignInPage(WebDriver chromeDriver){this.chromeDriver = chromeDriver;  chromeDriver.get(URL);}


    public ChromeSignInPage sendLogin(String login) {
        chromeDriver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(login);
        return this;
    }

    public ChromeSignInPage tapButton() {
        chromeDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]")).click();
        return this;
    }

    public ChromeSignInPage sendPassword(String password) {
        chromeDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        return this;
    }

    public ChromeSignInPage tapLoginButton() {
        chromeDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]")).click();
        return this;
    }
}
