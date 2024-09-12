package ad.create;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.ad.AdData;
import org.junit.Before;
import org.junit.Test;
import static org.example.ad.AdData.adInvalid;

public class NegativeSaveAd {
    private final AdData check = new AdData();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-internship.avito.com";
    }
    @Test
    public void createAdInvalidDataTest() {
        var ad = adInvalid();
        Response response =
                check.createAdInvalid(ad);
                check.answer(response); //По хорошему мы должны ожидать 400, но без документации понять сложно(Необходимо уточнение требований)
        System.out.println("Response body: " + response.asString());
    }
}

