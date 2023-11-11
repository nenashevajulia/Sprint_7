package ru.yandex.praktikum.login;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.praktikum.couriere.Courier;

import static ru.yandex.praktikum.couriere.CourierCreating.LOGIN;
import static ru.yandex.praktikum.couriere.CourierCreating.PASSWORD;

public class LoginAuthorization {

    @Step("Авторизация курьера")
    public static Login courierAuthorization(Courier courier) {
        Login login = new Login(LOGIN, PASSWORD);
        login.setLogin(courier.getLogin());
        login.setPassword(courier.getPassword());
        return login;
    }

    @Step("Авторизация курьера без логина")
    public static Login courierAuthorizationWithoutLogin() {
        Login login = new Login(LOGIN, PASSWORD);
        login.setPassword(RandomStringUtils.randomAlphanumeric(5));
        return login;
    }

    @Step("Авторизация курьера без пароля")
    public static Login courierAuthorizationWithoutPassword() {
        Login login = new Login(LOGIN, PASSWORD);
        login.setLogin(RandomStringUtils.randomAlphanumeric(5));
        return login;
    }

    @Step("Авторизация несуществующего курьера")
    public static Login courierNotExistingAuthorization() {
        Login login = new Login(LOGIN, PASSWORD);
        login.setLogin(RandomStringUtils.randomAlphanumeric(5));
        login.setPassword(RandomStringUtils.randomAlphanumeric(5));
        return login;
    }
}
