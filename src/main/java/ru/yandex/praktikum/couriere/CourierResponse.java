package ru.yandex.praktikum.couriere;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.praktikum.login.Login;

import static io.restassured.RestAssured.given;

public class CourierResponse {
    public static final String COURIER = "/api/v1/courier";
    public static final String COURIER_LOGIN = "/api/v1/courier/login";

    @Step("Создание курьера")
    public Response getCourierResponse(Courier courier) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(courier)
                .when()
                .post(COURIER);
    }

    @Step("Авторизация курьера")
    public Response getLoginResponse(Login login) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(login)
                .when()
                .post(COURIER_LOGIN);
    }
}
