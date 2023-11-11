import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Config;
import ru.yandex.praktikum.order.OrderResponse;

import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.Config.*;

public class ListOrderTest {
    private final OrderResponse orderResponse = new OrderResponse();


    @Before
    public void setUp() {
        Config.start();
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void checkGetOrderList() {

        Response response = orderResponse.getOrderList();

        response.then().statusCode(HttpStatus.SC_OK).and().assertThat().body(ORDERS, notNullValue());
    }
}
