
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Config;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.yandex.praktikum.CourierCreating.*;

public class CourierTest {
    private static final String COURIER = "/api/v1/courier";
    private static final String OK = "ok";
    private static final String MESSAGE = "message";
    private static final String EXISTING_LOGIN = "Этот логин уже используется. Попробуйте другой.";
    private static final String NO_DATA = "Недостаточно данных для создания учетной записи";

    @Before
    public void setUp() {
        RestAssured.baseURI = Config.getBaseUri();
    }

    @Test
    @DisplayName("Проверка создания курьера")
    public void checkCourierCreating() {
        Response response = given().contentType(ContentType.JSON).and().body(getCourier()).post(COURIER);
        response.then().assertThat().statusCode(HttpStatus.SC_CREATED).and().body(OK, equalTo(true));
    }

    @Test
    @DisplayName("Проверка создания двух одинаковых курьеров")
    public void checkCourierDuplicateCreating() {
        Response response = given().contentType(ContentType.JSON).and().body(getCourier()).post(COURIER);
        response.then().assertThat().statusCode(HttpStatus.SC_CONFLICT).and().body(MESSAGE, equalTo(EXISTING_LOGIN));
    }

    @Test
    @DisplayName("Проверка создания курьера без логина")
    public void checkLoginIsRequired() {
        Response response = given().contentType(ContentType.JSON).and().body(getCourierWithoutLogin()).post(COURIER);
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).and().body(MESSAGE, equalTo(NO_DATA));
    }

    @Test
    @DisplayName("Проверка создания курьера без пароля")
    public void checkPasswordIsRequired() {
        Response response = given().contentType(ContentType.JSON).and().body(getCourierWithoutPassword()).post(COURIER);
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).and().body(MESSAGE, equalTo(NO_DATA));
    }
}
