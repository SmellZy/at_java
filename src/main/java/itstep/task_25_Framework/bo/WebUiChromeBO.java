package itstep.task_25_Framework.bo;

import itstep.task_25_Framework.po.ChromeHomePage;
import itstep.task_25_Framework.po.ChromeIssuePage;
import itstep.task_25_Framework.po.ChromeSignInPage;
import org.openqa.selenium.WebDriver;

public class WebUiChromeBO {
    private WebDriver chromeDriver;
    private ChromeHomePage homePage;
    private ChromeIssuePage issuePage;
    private ChromeSignInPage signInPage;
    private String login;
    private String password;
    public WebUiChromeBO(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
    }

    public WebUiChromeBO signInUser() {
        signInPage = new ChromeSignInPage(chromeDriver);
        login = "e0db5b05-4";
        password = "root";
        signInPage.sendLogin(login)
                .tapButton()
                .sendPassword(password)
                .tapLoginButton();
        return this;
    }

    public WebUiChromeBO createIssue() {
        homePage = new ChromeHomePage(chromeDriver);

        homePage.goToIssuePage()
                .addDetailsToIssue();
        return this;
    }

    public WebUiChromeBO sendIssue() {
        issuePage = new ChromeIssuePage(chromeDriver);

        issuePage.sendIssue();
        return this;
    }
}
