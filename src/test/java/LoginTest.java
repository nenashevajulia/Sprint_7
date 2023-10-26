
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Config;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;
import static ru.yandex.praktikum.CourierCreating.getCourier;
import static ru.yandex.praktikum.LoginAuthorization.*;

import ru.yandex.praktikum.model.Courier;

public class LoginTest {
    private static final String COURIER = "/api/v1/courier";
    private static final String COURIER_LOGIN = "/api/v1/courier/login";
    private static final String MESSAGE = "message";
    public static final String ID = "id";
    public static final String COURIER_NOT_FOUND = "Учетная запись не найдена";
    public static final String NO_DATA = "Недостаточно данных для входа";

    @Before
    public void setUp() {
        RestAssured.baseURI = Config.getBaseUri();
    }


    @Test
    @DisplayName("Авторизация курьера")
    public void checkCourierAuthorization() {
        Courier courier = getCourier();
        given()

                .contentType(ContentType.JSON)
                .and()
                .body(courier)
                .post(COURIER);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .and()
                        .body(courierAuthorization(courier))
                        .post(COURIER_LOGIN);
        response.then().assertThat().statusCode(HttpStatus.SC_OK)
                .and()
                .body(ID, allOf(notNullValue(), greaterThan(0)));
    }

    @Test
    @DisplayName("Авторизация без поля login")
    public void checkCourierAuthorizationWithoutLogin() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(courierAuthorizationWithoutLogin())
                .post(COURIER_LOGIN);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .assertThat().body(MESSAGE, equalTo(NO_DATA));
    }

    @Test
    @DisplayName("Авторизация несуществующего пользователя")
    public void checkLoginErrorOfNonExistingCourier() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(courierNotExistingAuthorization())
                .post(COURIER_LOGIN);
        response.then().statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .assertThat().body(MESSAGE, equalTo(COURIER_NOT_FOUND));
    }
}

