package ru.yandex.praktikum.couriere;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierCreating {
    private static final String LOGIN = RandomStringUtils.randomAlphanumeric(5);
    private static final String PASSWORD = RandomStringUtils.randomAlphanumeric(5);
    private static final String NAME = "Курьер";

    @Step("Создание курьера со всеми обязательными полями")
    public static Courier getCourier() {
        Courier courier = new Courier();
        courier.setLogin(LOGIN);
        courier.setPassword(PASSWORD);
        courier.setFirstName(NAME);
        return courier;
    }

    @Step("Создание курьера без логина")
    public static Courier getCourierWithoutLogin() {
        Courier courier = new Courier();
        courier.setPassword(PASSWORD);
        courier.setFirstName(NAME);
        return courier;
    }

    @Step("Создание курьера без пароля")
    public static Courier getCourierWithoutPassword() {
        Courier courier = new Courier();
        courier.setLogin(LOGIN);
        courier.setFirstName(NAME);
        return courier;
    }


}
