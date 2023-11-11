import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.http.HttpStatus;
import ru.yandex.praktikum.Config;
import ru.yandex.praktikum.couriere.Courier;
import ru.yandex.praktikum.couriere.CourierResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static ru.yandex.praktikum.couriere.CourierCreating.*;
import static ru.yandex.praktikum.couriere.RemoveCourier.removeCourier;


public class CourierTest {

    private static final String OK = "ok";
    private static final String MESSAGE = "message";
    private static final String EXISTING_LOGIN = "Этот логин уже используется. Попробуйте другой.";
    private static final String NO_DATA = "Недостаточно данных для создания учетной записи";
    private final CourierResponse courierResponse = new CourierResponse();


    @Before
    public void setUp() {
        Config.start();
        courierResponse.getTestCourierResponse();
    }


    @Test
    @DisplayName("Проверка создания курьера")
    public void checkCourierCreating() {
        Courier courier = getCourier();
        Response response = courierResponse.getCourierResponse(courier);
        response.then().assertThat().statusCode(HttpStatus.SC_CREATED).and().body(OK, equalTo(true));
    }

    @Test
    @DisplayName("Проверка создания двух одинаковых курьеров")
    public void checkCourierDuplicateCreating() {
        Courier courier = new Courier(LOGIN, PASSWORD, NAME);
        Response response = courierResponse.getCourierResponse(courier);
        response.then().assertThat().statusCode(HttpStatus.SC_CONFLICT).and().body(MESSAGE, equalTo(EXISTING_LOGIN));
    }


    @Test
    @DisplayName("Проверка создания курьера без логина")
    public void checkLoginIsRequired() {
        Courier courier = getCourierWithoutLogin();
        Response response = courierResponse.getCourierResponse(courier);
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).and().body(MESSAGE, equalTo(NO_DATA));
    }

    @Test
    @DisplayName("Проверка создания курьера без пароля")
    public void checkPasswordIsRequired() {
        Courier courier = getCourierWithoutPassword();
        Response response = courierResponse.getCourierResponse(courier);
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).and().body(MESSAGE, equalTo(NO_DATA));
    }

    @After
    public void remove() {
        removeCourier();
    }
}
