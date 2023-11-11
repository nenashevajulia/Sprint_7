package ru.yandex.praktikum.couriere;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import ru.yandex.praktikum.login.Login;
import ru.yandex.praktikum.login.LoginAuthorization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.couriere.CourierCreating.*;
import static ru.yandex.praktikum.couriere.CourierResponse.COURIER;
import static ru.yandex.praktikum.couriere.CourierResponse.COURIER_LOGIN;

public class RemoveCourier {
    @Step("Удаление курьера")
    public static void removeCourier() {

        Login loginRequest = LoginAuthorization.courierAuthorization(getTestCourier());

        int id = given().contentType(ContentType.JSON).body(loginRequest).post(COURIER_LOGIN).then().assertThat().statusCode(HttpStatus.SC_OK).and().body("id", notNullValue()).extract().path("id");

        given().contentType(ContentType.JSON).delete(COURIER + '/' + id);
    }
}

