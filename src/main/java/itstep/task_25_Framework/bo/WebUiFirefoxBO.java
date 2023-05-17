package itstep.task_25_Framework.bo;

import itstep.task_25_Framework.po.*;
import org.openqa.selenium.WebDriver;

public class WebUiFirefoxBO{
    private WebDriver firefoxDriver;
    private FirefoxSignInPage signInPage;
    private FirefoxHomePage homePage;
    private FirefoxIssuePage issuePage;
    private String login;
    private String password;

    public WebUiFirefoxBO(WebDriver firefoxDriver){ this.firefoxDriver = firefoxDriver; }

    public WebUiFirefoxBO signIn() {
        signInPage = new FirefoxSignInPage(firefoxDriver);
        login = "1b3c530f-b";
        password = "root";
        signInPage.sendLogin(login)
                .tapButton()
                .sendPassword(password)
                .tapLoginButton();
        return this;
    }

    public WebUiFirefoxBO getIssue() {
        homePage = new FirefoxHomePage(firefoxDriver);

        homePage.goToIssuePage().tapCloseIssueButton()
                .addDetailsToIssue();
        return this;
    }

    public WebUiFirefoxBO closeIssue() {
        issuePage = new FirefoxIssuePage(firefoxDriver);

            issuePage.closeIssue();
        return this;
    }

    public WebUiFirefoxBO assertIssueIsClosed() {
        issuePage.assertIssueIsClosed();
        return this;
    }
}
