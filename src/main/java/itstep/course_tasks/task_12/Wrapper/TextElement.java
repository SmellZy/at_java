package itstep.course_tasks.task_12.Wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static itstep.course_tasks.task_12.WebDriverSupplier.driver;

//Divs:
//verifyDivExists - verifies if a div exists on the page
//waitForDivToBeVisible - waits for a div to be visible on the page
//getDivAttribute - retrieves the value of a specific attribute of a div

public class TextElement extends Element{
    public TextElement(WebElement webElement) {
        super(webElement);
    }


    public void waitForVisibleClick() {
        System.out.println("Starting waiting "+ getWebElement());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(getWebElement()));
        getWebElement().click();
    }

    public boolean verifyDivExists(By by) {
        return !driver.findElements(by).isEmpty();
    }

    public void waitForDivToBeVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String getDivAttribute(By by, String attributeName) {
        WebElement element = driver.findElement(by);
        return element.getAttribute(attributeName);
    }
}
