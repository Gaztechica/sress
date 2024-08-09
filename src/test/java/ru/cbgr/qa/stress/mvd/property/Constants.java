package ru.cbgr.qa.stress.mvd.property;

import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.substring;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Constants {

    /**
     * стенд для отладки на рабочих устройствах
     * */
//    public static final String BASE_URL = "http://79.137.187.118/ib_ZUP_1";

    /**
     * стенд для отладки запуска на оннланте
     * */
    public static final String BASE_URL = "http://23.105.246.172:5000";

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
            " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36";
    public static HttpProtocolBuilder httpProtocol = http
            .baseUrl(BASE_URL)
            .disableCaching()
            .userAgentHeader(USER_AGENT)
            .check(substring("Неизвестная форма").notExists().name("Нет ID формы"))
            .check(substring("\"caption\":\"Ошибка\"").notExists().name("Ошибка"))
            .check(substring("\"result\":[{\"error\"").notExists().name("Error"))
            .check(substring("Список работ пуст").notExists().name("Список работ пуст"))
            .check(substring("Вызов свойства или метода неинициализированного объекта")
                    .notExists().name("неинициализированный объект"))
            .check(substring("заблокирована пользователем").notExists().name("заблокирована пользователем"));
}
