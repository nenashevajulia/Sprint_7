package ru.yandex.praktikum;

public class Config {
    public static final String BASE_URI = "http://qa-scooter.praktikum-services.ru";
    public static String getBaseUri() {
        return BASE_URI;
    }

    public static final String GET_ORDERS = "/api/v1/orders";
    public static final String ORDERS = "orders";
}
