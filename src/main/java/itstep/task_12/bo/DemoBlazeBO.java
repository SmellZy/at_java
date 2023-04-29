package itstep.task_12.bo;

import itstep.task_12.po.HomePage;
import itstep.task_12.po.ProductPage;
import itstep.task_12.po.SingUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.UUID;

public class DemoBlazeBO {
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


    public DemoBlazeBO signUpRandomUser() throws InterruptedException {
        Thread.sleep(500);
        homePage = new HomePage();
        Thread.sleep(500);
        singUpPage = homePage.singUp();
        Thread.sleep(500);
//        Assert.assertTrue(singUpPage.idOpen());

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

        ProductPage productPage = new ProductPage();
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
