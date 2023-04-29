package itstep.task_12.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static itstep.task_12.WebDriverSupplier.driver;

public class CartPage {
    @FindBy(xpath = "//*[@id=\"tbodyid\"]/tr[1]/td[2]")
    private WebElement displayInCart;
    @FindBy(xpath = "//*[@id=\"page-wrapper\"]/div/div[2]/button")
    private WebElement placeOrder;
    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement inputName;
    @FindBy(xpath = "//*[@id=\"country\"]")
    private WebElement inputCountry;
    @FindBy(xpath = "//*[@id=\"city\"]")
    private WebElement inputCity;
    @FindBy(xpath = "//*[@id=\"card\"]")
    private WebElement inputCard;
    @FindBy(xpath = "//*[@id=\"month\"]")
    private WebElement inputMonth;
    @FindBy(xpath = "//*[@id=\"year\"]")
    private WebElement inputYear;
    @FindBy(xpath = "//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")
    private WebElement submitOrderButton;
    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement verifiedOrderWisible;
    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement verifyOrderButton;

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public CartPage productIsDisplayeInCart() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(displayInCart.isDisplayed());

        return this;
    }

    public CartPage placeOrder() throws InterruptedException {
        placeOrder.click();

        return this;
    }

    public CartPage inputName(String name) throws InterruptedException {
        Thread.sleep(1000);
        inputName.sendKeys(name);
        return this;
    }

    public CartPage inputCountry(String country) {
        inputCountry.sendKeys(country);
        return this;
    }

    public CartPage inputCity(String city) {
        inputCity.sendKeys(city);
        return this;
    }

    public CartPage inputCard(String creditCard) {
        inputCard.sendKeys(creditCard);
        return this;
    }

    public CartPage inputMonth(String month) {
        inputMonth.sendKeys(month);
        return this;
    }

    public CartPage inputYear(String year) {
        inputYear.sendKeys(year);
        return this;
    }

    public CartPage submitOrder() throws InterruptedException {
        submitOrderButton.click();
        return this;
    }

    public CartPage verifiedOrderWindow() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(verifiedOrderWisible.isDisplayed());
        verifyOrderButton.click();
        return this;
    }
}
