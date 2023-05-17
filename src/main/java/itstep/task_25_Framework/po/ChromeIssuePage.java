package itstep.task_25_Framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChromeIssuePage {
    private WebDriver chromeDriver;
    public ChromeIssuePage(WebDriver chromeDriver) {this.chromeDriver = chromeDriver;}

    public ChromeIssuePage addDetailsToIssue() {
        chromeDriver.findElement(By.xpath("//*[@id=\"reproducibility\"]")).click();
        WebElement waitMenu = new WebDriverWait(chromeDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"reproducibility\"]/option[2]")));
        waitMenu.click();
        //add відтворюваність: іноді
        chromeDriver.findElement(By.xpath("//*[@id=\"reproducibility\"]/option[2]")).click();
        //кому призначити
        chromeDriver.findElement(By.xpath("//*[@id=\"handler_id\"]")).click();
        //assign user: 1b3c530f-b
        WebElement chooseUser = new WebDriverWait(chromeDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"handler_id\"]/option[2]")));
        waitMenu.click();
        chromeDriver.findElement(By.xpath("//*[@id=\"handler_id\"]/option[2]")).click();
        //some statistic
        chromeDriver.findElement(By.xpath("//*[@id=\"summary\"]")).sendKeys("some statistic");
        //some description
        chromeDriver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("some description");
        return this;
    }

    public ChromeIssuePage sendIssue() {
        chromeDriver.findElement(By.xpath("//*[@id=\"report_bug_form\"]/div/div[2]/div[2]/input")).click();
        return this;
    }
}
