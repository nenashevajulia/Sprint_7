package ru.yandex.praktikum.couriere;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierCreating {


    public static final String LOGIN = "courier153";
    public static final String PASSWORD = "pass12345";
    public static final String NAME = "Курьер";

    @Step("Создание курьера со всеми обязательными полями")
    public static Courier getCourier() {
        Courier courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphanumeric(5));
        courier.setPassword(RandomStringUtils.randomAlphanumeric(5));
        courier.setFirstName(NAME);
        return courier;
    }

    @Step("Создание курьера с определенными значениями")
    public static Courier getTestCourier() {
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
