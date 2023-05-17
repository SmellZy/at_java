package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class FirefoxHomePage {
    @FindBy(xpath = "//*[@id=\"assigned\"]/div[2]/div/div/table/tbody/tr/td[2]/span[1]/a")
    private WebElement issuePage;


    private WebDriver firefoxDriver;

    public FirefoxHomePage(WebDriver firefoxDriver){PageFactory.initElements(this.firefoxDriver = firefoxDriver, this);}


    public FirefoxIssuePage goToIssuePage() {
        issuePage.click();
        return new FirefoxIssuePage(firefoxDriver);
    }
}
