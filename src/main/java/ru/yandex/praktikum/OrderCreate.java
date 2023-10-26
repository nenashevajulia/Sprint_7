package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.model.Order;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.Config.GET_ORDERS;

public class OrderCreate {

    @Step("Создание заказа")
    public ValidatableResponse getOrderCreate(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(GET_ORDERS)
                .then();
    }
}
