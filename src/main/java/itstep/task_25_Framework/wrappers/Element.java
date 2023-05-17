package itstep.task_25_Framework.wrappers;

import org.openqa.selenium.WebElement;

public class Element {
    public WebElement getWebElement() {
        return webElement;
    }

    public void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    private WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }
}
