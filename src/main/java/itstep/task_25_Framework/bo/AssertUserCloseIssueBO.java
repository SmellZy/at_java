package itstep.task_25_Framework.bo;

import itstep.course_tasks.Task_11.po.HomePage;
import itstep.task_25_Framework.po.ChromeHomePage;
import itstep.task_25_Framework.po.ChromeIssuePage;
import itstep.task_25_Framework.po.ChromeSignInPage;
import org.openqa.selenium.WebDriver;

public class AssertUserCloseIssueBO {
    private WebDriver chromeDriver;
    private String login;
    private String password;
    private ChromeSignInPage signInPage;
    private ChromeHomePage homePage;
    public AssertUserCloseIssueBO(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
    }

    public AssertUserCloseIssueBO singIn() {
        signInPage = new ChromeSignInPage(chromeDriver);
        login = "e0db5b05-4";
        password = "root";
        signInPage.sendLogin(login)
                .tapButton()
                .sendPassword(password)
                .tapLoginButton();
        return this;
    }

    public AssertUserCloseIssueBO checkIssueClosed() {
        homePage = new ChromeHomePage(chromeDriver);

        homePage.getAndCheckIssue();

        return this;
    }
}
