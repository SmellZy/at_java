package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChromeIssuePage {
    @FindBy(xpath = "//*[@id=\"reproducibility\"]")
    private WebElement reproducibilityList;
    @FindBy(xpath = "//*[@id=\"reproducibility\"]/option[2]")
    private WebElement reproducibilityOption;
    @FindBy(xpath = "//*[@id=\"handler_id\"]")
    private WebElement handler_list;
    @FindBy(xpath = "//*[@id=\"handler_id\"]/option[2]")
    private WebElement handler_user;
    @FindBy(xpath = "//*[@id=\"summary\"]")
    private WebElement statistic;
    @FindBy(xpath = "//*[@id=\"description\"]")
    private WebElement description;
    @FindBy(xpath = "//*[@id=\"report_bug_form\"]/div/div[2]/div[2]/input")
    private WebElement send_issue_button;



    private WebDriver chromeDriver;
    public ChromeIssuePage(WebDriver chromeDriver) {this.chromeDriver = chromeDriver;}

    public ChromeIssuePage addDetailsToIssue() {
        reproducibilityList.click();
        WebElement waitMenu = new WebDriverWait(chromeDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reproducibility\"]/option[2]")));
        waitMenu.click();
        //add відтворюваність: іноді
        reproducibilityOption.click();
        //кому призначити
        handler_list.click();
        //assign user: 1b3c530f-b
        WebElement chooseUser = new WebDriverWait(chromeDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"handler_id\"]/option[2]")));
        waitMenu.click();
        handler_user.click();
        //some statistic
        statistic.sendKeys("some statistic");
        //some description
        description.sendKeys("some description");
        return this;
    }

    public ChromeIssuePage sendIssue() {
        send_issue_button.click();
        return this;
    }
}
