package client;

import endpoints.EndPoints;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.User;
import models.UserCredentials;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .body(accessToken)
                .log().all()
                .delete(EndPoints.USER_PATH + "user")
                .then()
                .log().all();
    }

    @Step("Регистрация пользователя")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .log().all()
                .post(EndPoints.USER_PATH + "register")
                .then()
                .log().all();
    }

    @Step("Вход пользователя")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .log().all()
                .post(EndPoints.USER_PATH + "login")
                .then()
                .log().all();
    }
}
