package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChromeHomePage {
    private WebDriver chromeDriver;

    public ChromeHomePage(WebDriver chromeDriver){this.chromeDriver = chromeDriver;}


    public ChromeIssuePage goToIssuePage() {
        chromeDriver.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[3]/a")).click();
        return new ChromeIssuePage(chromeDriver);
    }
}
