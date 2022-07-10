package ru.krupskiy.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class UserClient {

    protected final String URL = "https://stellarburgers.nomoreparties.site/api";

    protected final RequestSpecification reqSpec = given()
            .header("Content-type", "application/json")
            .baseUri(URL);
    private final String ROOT = "/auth";
    private final String REGISTER = ROOT + "/register";
    private final String LOGIN = ROOT + "/login";
    private final String USER = ROOT + "/user";

    @Step("Создание нового пользователя")
    public ValidatableResponse createUser(User user) {
        return reqSpec
                .body(user)
                .when()
                .post(REGISTER)
                .then().log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return reqSpec
                .header("authorization", accessToken)
                .when()
                .delete(USER)
                .then().log().all();
    }
}
