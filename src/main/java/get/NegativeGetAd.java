package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.ad.AdData;
import org.junit.Before;
import org.junit.Test;
import static org.example.ad.AdData.*;

public class NegativeGetAd {
    private final AdData check = new AdData();
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-internship.avito.com";
    }
    @Test
    public void testGetAdInvalidId() {
        String invalidAdId = getInvalidAdId();

        Response response =
                check.getIdInvalid(invalidAdId); //Негативный тест с невалидным ID
                check.result(response);

        System.out.println("Response: " + response.asString());
}
    @Test
    public void testGetAdIsEmptyId() {
        Response response =
                check.getEmptyId(); //Негативный тест с пустым ID
                check.reply(response);

        System.out.println("Response: " + response.asString());
    }
    @Test
    public void testGetAdByInvalidSellerId() {
        String invalidSellerId = INVALID_SELLER_ID;

        Response response =
                check.getInvalidSellerID(invalidSellerId); //Если мы подразумеваем, что такой SellerId "существует", но у него нет объявлений, то этот ответ корректен, но если такого SellerId нет в системе, то по хорошему система должна возвращать 404.
                check.extracted(response); // Проверяем, что возвращается пустой массив

        System.out.println("Response: " + response.asString());
    }
}
