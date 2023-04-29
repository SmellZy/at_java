package task_11;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import itstep.Task_11.CartPage;
import itstep.Task_11.HomePage;
import itstep.Task_11.ProductPage;
import itstep.Task_11.SingUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

public class EndToEndTest {

    private WebDriver driver;
    @BeforeTest
    void setup(){
        //Setup ChromeDriver using driver file and properties.
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        //WebDriver driver = new ChromeDriver();


        //Setup ChromeDriver using DriverManager.
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }



    @Test
    void endToEndTest() throws InterruptedException {

        // target https://www.demoblaze.com

        HomePage homePage = new HomePage(driver);
        SingUpPage singUpPage = homePage.singUp();

        Assert.assertTrue(singUpPage.idOpen());

        String login = randomString();
        String password = randomString();

        homePage = singUpPage.inputLogin(login)
            .inputPassword(password)
            .singUp()
            .checkAndAcceptAllert();

        homePage.goToLoginPage()
                .inputLogin(login)
                .inputPassword(password)
                .login()
                .isLoggedIn();

        homePage.goToProduct()
                .addProductToCart()
                .acceptAllertWindow();

        String name = randomString();
        String country = randomString();
        String city  = randomString();
        String creditCard = randomString();
        String month = randomString();
        String year = randomString();

        ProductPage productPage = new ProductPage(driver);
        productPage.goToCart()
                .productIsDisplayeInCart()
                .placeOrder()
                .inputName(name)
                .inputCountry(country)
                .inputCity(city)
                .inputCard(creditCard)
                .inputMonth(month)
                .inputYear(year).submitOrder().verifiedOrderWindow();
    }

    private String randomString() {
        return UUID.randomUUID().toString().substring(0, 10).replace("-", "");
    }

    @AfterTest
    void shutDown(){
        driver.close();
        driver.quit();
    }
}
