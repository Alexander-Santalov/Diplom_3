package ru.yandex.stellarburger.api;

import io.qameta.allure.Step;
import ru.yandex.stellarburger.model.User;

import static io.restassured.RestAssured.given;

public class UserHelper {
    private static final String COURIER_PATH = "https://stellarburgers.nomoreparties.site/api/auth/";

    @Step("Отправка POST запроса на ручку api/auth/register для создания пользователя")
    public static String createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(COURIER_PATH + "register")
                .then()
                .log().all()
                .extract().path("accessToken");
    }

    @Step("Отправка DELETE запроса на ручку api/auth/user для удаления пользователя")
    public static void delete(String accessToken) {
        given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete(COURIER_PATH + "user")
                .then()
                .log().all();
    }

    @Step("Отправка POST запроса на ручку api/auth/login для логина пользователя в систему")
    public static String login(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(COURIER_PATH + "login")
                .then()
                .extract().path("accessToken");
    }

}