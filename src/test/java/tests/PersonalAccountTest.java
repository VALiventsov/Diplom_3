package tests;

import client.UserClient;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.After;
import org.junit.Test;
import pom.AccountPage;
import pom.LoginPage;
import pom.MainPage;
import utils.UserGenerator;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class PersonalAccountTest {

    LoginPage loginPage = page(LoginPage.class);
    AccountPage accountPage = page(AccountPage.class);
    private UserClient userClient;
    private User user;
    private String accessToken;

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @After
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void loginUserPersonalAccountButton() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickPersonalAccountButton();
        loginPage.setSingInButtonShouldBeVisible();
    }

    @Test
    @DisplayName("переход по клику на «Конструктор» из личного кабинета")
    public void checkGoToConstructor() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickLoginButton();

        userClient = new UserClient();
        user = UserGenerator.getRandom();
        ValidatableResponse createResponse = userClient.create(user);
        accessToken = createResponse.extract().path("accessToken");

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();

        mainPage.clickPersonalAccountButton();
        accountPage.clickCreateBurgerButton();
        mainPage.orderButtonShouldBeVisible();
    }

    @Test
    @DisplayName("переход по клику на логотип Stellar Burgers из личного кабинета")
    public void checkOutFromPersonalAccount() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickLoginButton();

        userClient = new UserClient();
        user = UserGenerator.getRandom();
        ValidatableResponse createResponse = userClient.create(user);
        accessToken = createResponse.extract().path("accessToken");

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();
        mainPage.clickPersonalAccountButton();
        accountPage.clickStellarBurgerLogoButton();
        mainPage.orderButtonShouldBeVisible();
    }
}
