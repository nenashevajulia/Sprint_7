import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import ru.yandex.praktikum.Config;
import ru.yandex.praktikum.order.Order;
import ru.yandex.praktikum.order.OrderResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String firstNameValue;
    private final String lastNameValue;
    private final String addressValue;
    private final int metroStationValue;
    private final String phoneValue;
    private final int rentTimeValue;
    private final String deliveryDateValue;
    private final String commentValue;
    private final List<String> colorValue;

    public OrderTest(String firstNameValue, String lastNameValue, String addressValue, int metroStationValue, String phoneValue, int rentTimeValue, String deliveryDateValue, String commentValue, List<String> colorValue) {
        this.firstNameValue = firstNameValue;
        this.lastNameValue = lastNameValue;
        this.addressValue = addressValue;
        this.metroStationValue = metroStationValue;
        this.phoneValue = phoneValue;
        this.rentTimeValue = rentTimeValue;
        this.deliveryDateValue = deliveryDateValue;
        this.commentValue = commentValue;
        this.colorValue = colorValue;
    }

    @Parameterized.Parameters
    public static Object[][] getTestDataCreateOrder() {
        return new Object[][]{{"Никитин", "Герман", "Саринский пр-д, 95", 4, "+79874563259", 5, "05.09.2023", "комментарий заказа", null}, {"Никитин", "Герман", "Саринский пр-д, 95", 4, "+79874563259", 5, "05.09.2023", "комментарий заказа", List.of("BLACK")}, {"Никитин", "Герман", "Саринский пр-д, 95", 4, "+79874563259", 5, "05.09.2023", "комментарий заказа", List.of("GREY")}, {"Никитин", "Герман", "Саринский пр-д, 95", 4, "+79874563259", 5, "05.09.2023", "комментарий заказа", List.of("BLACK", "GREY")},};
    }

    @Before
    public void setUp() {
        Config.start();
    }

    @DisplayName("Создание заказа")
    @Test
    public void createOrderTest() {
        OrderResponse orderCreate = new OrderResponse();
        ValidatableResponse emptyPasswordField = orderCreate.getOrderCreate(new Order(firstNameValue, lastNameValue, addressValue, metroStationValue, phoneValue, rentTimeValue, deliveryDateValue, commentValue, colorValue));
        emptyPasswordField.statusCode(HttpStatus.SC_CREATED);
        MatcherAssert.assertThat("track", notNullValue());
    }
}