package org.karina.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.karina.factory.PrimeNumberCalculatorType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIT {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testGetValidResponseWhenGivenNumber() {
        given().basePath("/api")
                .get("/calculate?n=10")
                .then()
                .statusCode(200)
                .body("n", equalTo(10))
                .and()
                .body("primeNumberList", equalTo("[2, 3, 5, 7]"))
                .and()
                .body("algorithm", equalTo("ERATOSTHENES"));
    }

    @ParameterizedTest
    @EnumSource(PrimeNumberCalculatorType.class)
    void testGetValidResponseWhenGivenNumberAndAlgorithm(PrimeNumberCalculatorType algorithm) {
        given().basePath("/api")
                .get("/calculate?n=10&algorithm=" + algorithm)
                .then()
                .statusCode(200)
                .body("n", equalTo(10))
                .and()
                .body("primeNumberList", equalTo("[2, 3, 5, 7]"))
                .and()
                .body("algorithm", equalTo(algorithm.toString()));
    }

    @Test
    void testGetErrorResponseWhenGivenNonValidAlgorithm() {
        List<String> validAlgorithms = new ArrayList<>();
        for (PrimeNumberCalculatorType type : PrimeNumberCalculatorType.values()) {
            validAlgorithms.add(type.toString());
        }
        given().basePath("/api")
                .get("/calculate?n=10&algorithm=INVALID")
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid algorithm. Please choose one of the following: " + validAlgorithms));
    }

    @Test
    void testGetErrorResponseWhenGivenNumberLessThan2() {
        given().basePath("/api")
                .get("/calculate?n=1")
                .then()
                .statusCode(400)
                .body("message", equalTo("n must be greater than 1"));
    }

    @Test
    void testGetErrorResponseWhenGivenNonNumber() {
        given().basePath("/api")
                .get("/calculate?n=abcd")
                .then()
                .statusCode(400)
                .body("message", equalTo("n must be a number"));
    }
}
