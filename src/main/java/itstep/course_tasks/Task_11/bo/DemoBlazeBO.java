package itstep.course_tasks.Task_11.bo;

import itstep.course_tasks.Task_11.po.HomePage;
import itstep.course_tasks.Task_11.po.ProductPage;
import itstep.course_tasks.Task_11.po.SingUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.UUID;

public class DemoBlazeBO {
    private WebDriver driver;
    private HomePage homePage;
    private SingUpPage singUpPage;
    private String login;
    private String password;
    private String name;
    private String country;
    private String city;
    private String creditCard;
    private String month;
    private String year;

    public DemoBlazeBO(WebDriver driver) {
        this.driver = driver;
    }

    public DemoBlazeBO signUpRandomUser() throws InterruptedException {
        homePage = new HomePage(driver);

        singUpPage = homePage.singUp();

        Assert.assertTrue(singUpPage.idOpen());

        login = randomString();
        password = randomString();

        homePage = singUpPage.inputLogin(login)
                .inputPassword(password)
                .singUp()
                .checkAndAcceptAllert();
        return this;
    }
    private String randomString() {
        return UUID.randomUUID().toString().substring(0, 10).replace("-", "");
    }

    public DemoBlazeBO loginUser() throws InterruptedException {
        homePage.goToLoginPage()
                .inputLogin(login)
                .inputPassword(password)
                .login()
                .isLoggedIn();
        return this;
    }

    public DemoBlazeBO addToCart() throws InterruptedException {
        homePage.goToProduct()
                .addProductToCart()
                .acceptAllertWindow();
        return this;
    }

    public void verifyOrder() throws InterruptedException {
        name = randomString();
        country = randomString();
        city  = randomString();
        creditCard = randomString();
        month = randomString();
        year = randomString();

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
}
