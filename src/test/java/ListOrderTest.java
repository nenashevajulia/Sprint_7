
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Config;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.Config.GET_ORDERS;
import static ru.yandex.praktikum.Config.ORDERS;

public class ListOrderTest {


    @Before
    public void setUp() {
        RestAssured.baseURI = Config.getBaseUri();
    }

    @Test
    @DisplayName("Список заказов")
    public void checkGetOrderList() {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .and()
                        .queryParam("limit", 10)
                        .queryParam("page", 0)
                        .get(GET_ORDERS);
        response.then().assertThat().statusCode(HttpStatus.SC_OK)
                .and()
                .body(ORDERS, notNullValue());
    }
}
