package com.fisglobal.d1pf.poc.http;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SimpleInterHttpResourceTest {

    @Test
    public void testPingEndpoint() {
        given()
                .when().get("/innerhttp")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }

    @Test
    public void testEchoEndpoint() {
        String uuid = UUID.randomUUID().toString();
        given()
                .pathParam("name", uuid)
                .when().get("/innerhttp/echo/{name}")
                .then()
                .statusCode(200);
//                .body(is("hello " + uuid));
    }

}
