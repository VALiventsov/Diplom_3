package tests;

import client.UserClient;
import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.User;
import models.UserCredentials;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import pom.LoginPage;
import pom.MainPage;
import pom.RegisterPage;
import utils.UserGenerator;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationUserTest {

    private String accessToken;
    private UserClient userClient;

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрации")
    public void checkRegistration() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        UserClient userClient = new UserClient();
        User user = UserGenerator.getRandom();
        mainPage.clickLoginButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.registerButtonClick();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.registration(user.getName(), user.getEmail(), user.getPassword());

        UserCredentials userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        int statusCode = loginResponse
                .extract()
                .statusCode();

        assertThat(statusCode, Matchers.equalTo(SC_OK));
        String accessToken = loginResponse.extract().path("accessToken");
    }

    @Test
    @DisplayName("Ошибка некорректного пароля. Минимальный пароль — шесть символов")
    public void checkRegistrationWithIncorrectPassword() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        UserClient userClient = new UserClient();
        User user = UserGenerator.getIncorrectPasswordRandom();
        mainPage.clickLoginButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.registerButtonClick();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.registration(user.getName(), user.getEmail(), user.getPassword());
        registerPage.getTextError().shouldBe(Condition.visible);

        UserCredentials userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        int statusCode = loginResponse
                .extract()
                .statusCode();

        assertThat(statusCode, Matchers.equalTo(SC_UNAUTHORIZED));
    }
}
