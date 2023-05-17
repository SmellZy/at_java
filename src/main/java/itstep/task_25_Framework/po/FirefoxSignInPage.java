package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirefoxSignInPage {
    private WebDriver firefoxDriver;
    public static final String URL = "http://localhost/login_page.php";
    public FirefoxSignInPage(WebDriver firefoxDriver){this.firefoxDriver = firefoxDriver;  firefoxDriver.get(URL);}


    public FirefoxSignInPage sendLogin(String login) {
        firefoxDriver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(login);
        return this;
    }

    public FirefoxSignInPage tapButton() {
        firefoxDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]")).click();
        return this;
    }

    public FirefoxSignInPage sendPassword(String password) {
        firefoxDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        return this;
    }

    public FirefoxSignInPage tapLoginButton() {
        firefoxDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]")).click();
        return this;
    }
}
