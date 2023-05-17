package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChromeHomePage {
    @FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[3]/a")
    private WebElement sideBar;

    private WebDriver chromeDriver;

    public ChromeHomePage(WebDriver chromeDriver){PageFactory.initElements(this.chromeDriver = chromeDriver, this);}


    public ChromeIssuePage goToIssuePage() {
        sideBar.click();
        return new ChromeIssuePage(chromeDriver);
    }
}
