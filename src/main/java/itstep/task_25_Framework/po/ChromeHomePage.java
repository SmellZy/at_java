package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChromeHomePage {
    @FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[3]/a")
    private WebElement sideBar;

    private WebDriver chromeDriver;

    public ChromeHomePage(WebDriver chromeDriver){this.chromeDriver = chromeDriver;}


    public ChromeIssuePage goToIssuePage() {
        sideBar.click();
        return new ChromeIssuePage(chromeDriver);
    }
}
