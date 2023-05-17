package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class FirefoxIssuePage {
    @FindBy(xpath = "//*[@id=\"bugnote_text\"]")
    private WebElement commentField;
    @FindBy(xpath = "//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tfoot/tr/td/div/div[6]/form/fieldset/input[5]")
    private WebElement closeIssueButton;
    @FindBy(xpath = "//*[@id=\"bug-change-status-form\"]/fieldset/div/div[2]/div[2]/input")
    private WebElement closeIssue;
    @FindBy(xpath = "//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[7]/td[2]")
    private WebElement statusField;

    private WebDriver firefoxDriver;
    public FirefoxIssuePage(WebDriver firefoxDriver) {this.firefoxDriver = firefoxDriver;}

    public FirefoxIssuePage addDetailsToIssue() {
        commentField.sendKeys("some comment");
        return this;
    }

    public FirefoxIssuePage tapCloseIssueButton() {
        closeIssueButton.click();
        return this;
    }

    public FirefoxIssuePage closeIssue() {
        closeIssue.click();
        return this;
    }

    public FirefoxIssuePage assertIssueIsClosed() {
        String issueText = statusField.getText();
        Assert.assertEquals(issueText, "решена");
        return this;
    }
}
