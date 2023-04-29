package itstep.Task_11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductPage {
    private WebDriver driver;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductPage addProductToCart() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();

        Thread.sleep(1300);
        return this;
    }

    public void acceptAllertWindow() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertEquals(driver.switchTo().alert().getText(), "Product added.", "unexpected message");
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
    }

    public CartPage goToCart() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"cartur\"]")).click();
        Thread.sleep(1000);
        return new CartPage(driver);
    }
}
