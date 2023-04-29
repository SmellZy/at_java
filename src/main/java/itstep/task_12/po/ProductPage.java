package itstep.task_12.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static itstep.task_12.WebDriverSupplier.driver;

public class ProductPage {

    @FindBy(xpath = "//*[@id=\"cartur\"]")
    private WebElement cart;
    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[2]/div/a")
    private WebElement addToCart;

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    public ProductPage addProductToCart() throws InterruptedException {
        Thread.sleep(3000);
        addToCart.click();
        return this;
    }

    public void acceptAllertWindow() throws InterruptedException {
        Thread.sleep(700);
        Assert.assertEquals(driver.switchTo().alert().getText(), "Product added.", "unexpected message");
        driver.switchTo().alert().accept();
    }

    public CartPage goToCart() throws InterruptedException {
        cart.click();
        return new CartPage();
    }
}
