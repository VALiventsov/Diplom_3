package tests;

import client.UserClient;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.After;
import org.junit.Test;
import pom.LoginPage;
import pom.MainPage;
import pom.RecoverPasswordPage;
import pom.RegisterPage;
import utils.UserGenerator;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LoginUserTest {

    LoginPage loginPage = page(LoginPage.class);
    RegisterPage registerPage = page(RegisterPage.class);
    RecoverPasswordPage recoverPasswordPage = page(RecoverPasswordPage.class);
    private String accessToken;
    private UserClient userClient;
    private User user;

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

    @org.junit.Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginUserLoginButton() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        ValidatableResponse createResponse = userClient.create(user);
        accessToken = createResponse.extract().path("accessToken");

        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickLoginButton();

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();

        mainPage.orderButtonShouldBeVisible();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginUserPersonalAccountButton() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        ValidatableResponse createResponse = userClient.create(user);
        accessToken = createResponse.extract().path("accessToken");

        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickPersonalAccountButton();

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();

        mainPage.orderButtonShouldBeVisible();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginUserByRegistrationForm() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        ValidatableResponse createResponse = userClient.create(user);
        accessToken = createResponse.extract().path("accessToken");

        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickLoginButton();
        loginPage.registerButtonClick();
        registerPage.clickSingInButton();

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();

        mainPage.orderButtonShouldBeVisible();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginUserByRecoverPasswordButton() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        ValidatableResponse createResponse = userClient.create(user);
        accessToken = createResponse.extract().path("accessToken");

        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickLoginButton();
        loginPage.clickRecoverPasswordButton();
        recoverPasswordPage.clickLoginButton();

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();

        mainPage.orderButtonShouldBeVisible();
    }
}

