package org.example.ad;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.net.HttpURLConnection;
import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
public class AdData {
    public static final String PATH_POST = "/api/1/item";
    public static final String PATH_GET = "/api/1/item/{id}";
    public static final String GET_SELLER_ID = "/api/1/{sellerID}/item";
    public static final String GET_INVALID = "/api/1/item/";
    public static final String INVALID_SELLER_ID = "23541";
    public static String adData() {
        var ad = "{\"name\": \"Телефон\", \"price\": 85566, \"sellerId\": 222222, \"statistics\": {\"contacts\": 32, \"like\": 35, \"viewCount\": 14}}";
        return ad;
    }
    public static String adInvalid() {
        var ad = "{\"name\": 400, \"price\": 85566, \"sellerId\": 222222, \"statistics\": {\"contacts\": 32, \"like\": 35, \"viewCount\": 14}}";
        return ad;
    }
    public static String getSellerId() {
        return "222222";
    }
    public static String getInvalidAdId() {
        String invalidAdId = "304ee7a7-2ecc-4962-b4a9-157cf6e84as";
        return invalidAdId;
    }
    public static Response pullPen(String ad) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .and()
                .body(ad)
                .when()
                .post(PATH_POST);
    }
    public static void successCreate(Response response) {
        response.then().assertThat().body("status", notNullValue())
                .and()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().response();
    }
    public static Response createAdInvalid(String ad) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .and()
                .body(ad)
                .when()
                .post(PATH_POST);
    }
    public static ValidatableResponse answer(Response response) {
        return response.then()
                .statusCode(HttpURLConnection.HTTP_SERVER_ERROR);
    }
    public static Response getId(String adId) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", adId)
                .when()
                .get(PATH_GET);
    }
    public static void success(Response response) {
        response.then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("id", notNullValue())
                .extract().response();
    }
    public static Response getSellerID(String sellerId) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("sellerID", sellerId)
                .when()
                .get(GET_SELLER_ID);
    }
    public static void successSellerId(Response response) {
        response.then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("sellerId", notNullValue())
                .extract().response();
    }
    public static Response getIdInvalid(String invalidAdId) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", invalidAdId)
                .when()
                .get(PATH_GET);
    }
    public static void result(Response response) {
        response.then()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .extract().response();
    }
    public static Response getEmptyId() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(GET_INVALID);
    }
    public static void reply(Response response) {
        response.then()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body("message", equalTo("route /api/1/item/ not found"))
                .extract().response();
    }
    public static Response getInvalidSellerID(String invalidSellerId) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("sellerID", invalidSellerId)
                .when()
                .get(GET_SELLER_ID);
    }
    public static void extracted(Response response) {
        response.then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("$", empty())
                .extract().response();
    }

}
