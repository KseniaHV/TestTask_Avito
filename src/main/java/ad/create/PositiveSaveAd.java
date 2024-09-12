package ad.create;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.ad.AdData;
import org.junit.Before;
import org.junit.Test;
import static org.example.ad.AdData.adData;

public class PositiveSaveAd {
    private final AdData check = new AdData();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-internship.avito.com";
    }
    @Test
    public void createAdSuccessTest() {
        var ad = adData();
        Response response =
                check.pullPen(ad);                  // Дергаем ручку
                check.successCreate(response);      // Проверка ответа

        System.out.println("Response body: " + response.asString());   // Выводим полный ответ в консоль
    }
}
