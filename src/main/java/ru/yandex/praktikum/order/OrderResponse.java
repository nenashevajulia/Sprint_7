package ru.yandex.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.Config.GET_ORDERS;

public class OrderResponse {

    @Step("Создание заказа")
    public ValidatableResponse getOrderCreate(Order order) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(order)
                .when()
                .post(GET_ORDERS)
                .then();
    }
}
