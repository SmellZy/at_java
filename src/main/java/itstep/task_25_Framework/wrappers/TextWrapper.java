package itstep.task_25_Framework.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static itstep.course_tasks.task_12.WebDriverSupplier.driver;


public class TextWrapper extends Element {
    public TextWrapper(WebElement webElement) {
        super(webElement);
    }


    public void waitForElementVisibleClick() {
        System.out.println("Starting waiting "+ getWebElement());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(getWebElement()));
        getWebElement().click();
    }

    public void waitForDivElementToBeVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
