package ru.yandex.praktikum.couriere;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import ru.yandex.praktikum.login.Login;
import ru.yandex.praktikum.login.LoginAuthorization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.couriere.CourierResponse.COURIER;
import static ru.yandex.praktikum.couriere.CourierResponse.COURIER_LOGIN;

public class RemoveCourier {
    @Step("Получение id курьера для удаления")
    public static int getId(Courier courier) {
        Login loginRequest = LoginAuthorization.courierAuthorization(courier);

        int id = given().contentType(ContentType.JSON).body(loginRequest).post(COURIER_LOGIN).then().assertThat().statusCode(HttpStatus.SC_OK).and().body("id", notNullValue()).extract().path("id");
        return id;
    }

    @Step("Удаление курьера")
    public static ValidatableResponse removeCourier(int id) {
        return given().contentType(ContentType.JSON).when().delete(COURIER + '/' + id).then();
    }
}

