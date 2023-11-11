import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Config;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;
import static ru.yandex.praktikum.couriere.CourierCreating.getTestCourier;
import static ru.yandex.praktikum.couriere.RemoveCourier.removeCourier;
import static ru.yandex.praktikum.login.LoginAuthorization.*;


import ru.yandex.praktikum.couriere.Courier;
import ru.yandex.praktikum.couriere.CourierResponse;
import ru.yandex.praktikum.login.Login;

public class LoginTest {

    private static final String MESSAGE = "message";
    public static final String ID = "id";
    public static final String COURIER_NOT_FOUND = "Учетная запись не найдена";
    public static final String NO_DATA = "Недостаточно данных для входа";
    private final CourierResponse courierResponse = new CourierResponse();

    @Before
    public void setUp() {
        Config.start();
        courierResponse.getTestCourierResponse();
    }


    @Test
    @DisplayName("Авторизация курьера")
    public void checkCourierAuthorization() {
        Courier courier = getTestCourier();
        courierResponse.getCourierResponse(getTestCourier());
        Login login = courierAuthorization(courier);
        Response response = courierResponse.getLoginResponse(login);
        response.then().assertThat().statusCode(HttpStatus.SC_OK).and().body(ID, allOf(notNullValue(), greaterThan(0)));
    }

    @Test
    @DisplayName("Авторизация без поля login")
    public void checkCourierAuthorizationWithoutLogin() {
        Login login = courierAuthorizationWithoutLogin();
        Response response = courierResponse.getLoginResponse(login);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).and().assertThat().body(MESSAGE, equalTo(NO_DATA));
    }

    @Test
    @DisplayName("Авторизация несуществующего пользователя")
    public void checkLoginErrorOfNonExistingCourier() {
        Login login = courierNotExistingAuthorization();
        Response response = courierResponse.getLoginResponse(login);
        response.then().statusCode(HttpStatus.SC_NOT_FOUND).and().assertThat().body(MESSAGE, equalTo(COURIER_NOT_FOUND));
    }

    @After
    public void remove() {
        removeCourier();
    }
}

