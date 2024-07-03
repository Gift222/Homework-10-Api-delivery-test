package org.example;

import Dto.TestOrderDto;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.TestDataGenerator;

public class ApiDeliveryTest {
    public static final String BASE_URL = "https://backend.tallinn-learning.ee";
    public static final String BASE_PATH = "/test-orders/";
    String responseBody = RestAssured;
    private byte[] requestBodyLongWay;
    long receivedOrderId = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBodyLongWay)
            .log()
            .all()
            .post(BASE_URL + BASE_PATH)
            .then()
            .log()
            .all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .extract()
            .path("id");
    private String customerName;
    private String customerPhone;

    @Test
    public void checkOrderCreationId() {
        String requestBodyLongWay = "{\n" +
                "  \"status\": \"OPEN\",\n" +
                "  \"courierId\": 0,\n" +
                "  \"customerName\": \"0\",\n" +
                "  \"customerPhone\": \"0\",\n" +
                "  \"comment\": \"0\",\n" +
                "  \"id\": 0\n" +
                "}";
    }

    @Test
    public void deleteAnExistingorder() {
        int orderIDRequested = 8;
        RestAssured
                .given()
                .log()
                .all()
                .delete(BASE_URL + BASE_PATH + orderIDRequested)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void updateAnExistingOrderDetails() {
        RestAssured
                .given()
                .log()
                .all()
                .put(BASE_URL + BASE_PATH + "4")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void getCustomerNameWithParameter() {
        int orderIdRequested = 3;
        String orderIdReuested;
        String amendedOrderDetails = RestAssured
                .given()
                .log()
                .all()
                .queryParam("customerName")
                .get(BASE_URL + BASE_PATH + orderIdReuested)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .path("customerName");
        Assertions.assertEquals("connor", amendedOrderDetails);

    }


    //Assignment 11

    @Test
    public void createOrderWithDtoPattern() {

        //Order creation
        TestOrderDto orderDtoRequest;
        orderDtoRequest = new TestOrderDto(customerName, "9084399", "Drive me to work");
        //serialization from java to json
        String requestBodyAsJson = new Gson().toJson(orderDtoRequest);
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBodyAsJson)
                .log()
                .all()
                .post()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createOrderWithDtoPatternAndSetters() {

        //Order creation by default
        TestOrderDto orderDtoRequest = new TestOrderDto();
        orderDtoRequest.setComment("Make yourself comfortable");
        orderDtoRequest.setCustomerName("Mary");
        orderDtoRequest.setCustomerPhone("99999963");

        //serialization from java to json
        String requestBodyAsJson = new Gson().toJson(orderDtoRequest);
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBodyAsJson)
                .log()
                .all()
                .post()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createOrderWithDtoPatternAndSettersAndRandomValues() {

        //Order creation by default
        TestOrderDto orderDtoRequest = new TestOrderDto();
        String comment = RandomStringUtils.randomAlphabetic(8);
        String CustomerName = RandomStringUtils.randomAlphabetic(5);
        String CustomerPhone = RandomStringUtils.randomNumeric(8);
        orderDtoRequest.setComment(RandomStringUtils.randomAlphabetic(8));
        orderDtoRequest.setCustomerName(RandomStringUtils.randomAlphabetic(5));
        orderDtoRequest.setCustomerPhone(RandomStringUtils.randomNumeric(8));

        //serialization from java to json
        String requestBodyAsJson = new Gson().toJson(orderDtoRequest);
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBodyAsJson)
                .log()
                .all()
                .post()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createOrderWithDtoPatternAndSettersAndRandomValues() {

        //Order creation by default
        TestOrderDto orderDtoRequest = new TestOrderDto();
        orderDtoRequest.setComment(TestDataGenerator.generateRandomComment());
        orderDtoRequest.setCustomerName(TestDataGenerator.generateRandomCustomerName());
        orderDtoRequest.setCustomerPhone(TestDataGenerator.generateRandomCustomerPhone());

        //serialization from java to json
        String requestBodyAsJson = new Gson().toJson(orderDtoRequest);
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBodyAsJson)
                .log()
                .all()
                .post()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }


    @Test
    public void createOrderWithDtoPatternHalfRandomValuesHalfFakerValues() {
        TestOrderDto orderDtoRequest = new TestOrderDto();
        orderDtoRequest.setCustomerName(TestDataGenerator.generateRandomCustomerName());
        orderDtoRequest.setCustomerPhone(TestDataGenerator.generateRandomCustomerPhone());
        orderDtoRequest.setComment(TestDataGenerator.generateRandomComment());
        String requestBodyAsJson = new Gson().toJson(orderDtoRequest);
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBodyAsJson)
                .log()
                .all()
                .post()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);


    }

