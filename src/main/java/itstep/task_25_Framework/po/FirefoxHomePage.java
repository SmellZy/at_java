package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirefoxHomePage {
    private WebDriver firefoxDriver;

    public FirefoxHomePage(WebDriver firefoxDriver){this.firefoxDriver = firefoxDriver;}


    public FirefoxIssuePage goToIssuePage() {
        firefoxDriver.findElement(By.xpath("//*[@id=\"assigned\"]/div[2]/div/div/table/tbody/tr/td[2]/span[1]/a")).click();
        return new FirefoxIssuePage(firefoxDriver);
    }
}
