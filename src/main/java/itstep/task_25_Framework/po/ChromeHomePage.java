package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ChromeHomePage {
    @FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[3]/a")
    private WebElement sideBar;
    @FindBy(xpath = "//*[@id=\"timeline\"]/div[2]/div[4]")
    private WebElement issueContainer;

    private WebDriver chromeDriver;

    public ChromeHomePage(WebDriver chromeDriver){PageFactory.initElements(this.chromeDriver = chromeDriver, this);}


    public ChromeIssuePage goToIssuePage() {
        sideBar.click();
        return new ChromeIssuePage(chromeDriver);
    }

    public ChromeHomePage getAndCheckIssue() {
        String issueText = issueContainer.getText();
        int cutoffIndex = issueText.indexOf("1b3c530f-b закрив проблему");
        String cutoffText;
        if (cutoffIndex != -1) {
            cutoffText = issueText.substring(0, cutoffIndex + "1b3c530f-b закрив проблему".length()).trim();
        } else {
            cutoffText = issueText.trim();
        }
        Assert.assertEquals(cutoffText, "1b3c530f-b закрив проблему");
        return this;
    }
}
