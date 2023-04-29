package itstep.Task_11.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage productIsDisplayeInCart() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr[1]/td[2]")).isDisplayed());
        Thread.sleep(500);
        return this;
    }

    public CartPage placeOrder() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
        Thread.sleep(500);
        return this;
    }

    public CartPage inputName(String name) {
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(name);
        return this;
    }

    public CartPage inputCountry(String country) {
        driver.findElement(By.xpath("//*[@id=\"country\"]")).sendKeys(country);
        return this;
    }

    public CartPage inputCity(String city) {
        driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys(city);
        return this;
    }

    public CartPage inputCard(String creditCard) {
        driver.findElement(By.xpath("//*[@id=\"card\"]")).sendKeys(creditCard);
        return this;
    }

    public CartPage inputMonth(String month) {
        driver.findElement(By.xpath("//*[@id=\"month\"]")).sendKeys(month);
        return this;
    }

    public CartPage inputYear(String year) {
        driver.findElement(By.xpath("//*[@id=\"year\"]")).sendKeys(year);
        return this;
    }

    public CartPage submitOrder() throws InterruptedException {
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
        return this;
    }

    public CartPage verifiedOrderWindow() throws InterruptedException {
        Thread.sleep(200);
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[10]")).isDisplayed());
        driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click();
        return this;
    }
}
