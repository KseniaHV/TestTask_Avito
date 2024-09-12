package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.ad.AdData;
import org.junit.Before;
import org.junit.Test;
import static org.example.ad.AdData.*;

public class PositiveGetAd {
    private final AdData check = new AdData();
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-internship.avito.com";
    }
    @Test
    public void successGetAdTest() {
        var ad = adData();
        Response response =
                check.pullPen(ad);
                check.successCreate(response); //Создаем заказ, чтобы после его получить

        String responseBody = response.asString(); // Получаем тело ответа как строку для извлечения id

        String adId = responseBody.split("Сохранили объявление - ")[1].replace("\"", "").replace("}", "").trim(); // Извлечение ID из поля status

        System.out.println("Создано объявление с ID: " + adId); // Выводим id в консоль

        Response getResponse =
                check.getId(adId); //Получаем объявление по ID
                check.success(response); //Проверяем

        System.out.println("Response: " + getResponse.asString());  //Выводим ответ в консоль
    }
    @Test
    public void successGetAdBySellerIdTest(){
            String sellerId = getSellerId();
            Response response =
                    check.getSellerID(sellerId); //Получаем все объявления по SellerId
                    check.successSellerId(response); //Проверка

        System.out.println("Response: " + response.asString()); //Выводим все объявления продавца по SellerId

    }
}
