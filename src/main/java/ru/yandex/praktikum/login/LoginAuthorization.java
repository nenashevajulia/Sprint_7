package ru.yandex.praktikum.login;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.praktikum.couriere.Courier;
import ru.yandex.praktikum.login.Login;

public class LoginAuthorization {
    private static final String LOGIN = RandomStringUtils.randomAlphanumeric(5);
    private static final String PASSWORD = RandomStringUtils.randomAlphanumeric(5);

    @Step("Авторизация курьера")
    public static Login courierAuthorization(Courier courier) {
        Login login = new Login();
        login.setLogin(courier.getLogin());
        login.setPassword(courier.getPassword());
        return login;
    }

    @Step("Авторизация курьера без логина")
    public static Login courierAuthorizationWithoutLogin() {
        Login login = new Login();
        login.setPassword(PASSWORD);
        return login;
    }

    @Step("Авторизация курьера без пароля")
    public static Login courierAuthorizationWithoutPassword() {
        Login login = new Login();
        login.setLogin(LOGIN);
        return login;
    }

    @Step("Авторизация несуществующего курьера")
    public static Login courierNotExistingAuthorization() {
        Login login = new Login();
        login.setLogin(LOGIN);
        login.setPassword(PASSWORD);
        return login;
    }
}
