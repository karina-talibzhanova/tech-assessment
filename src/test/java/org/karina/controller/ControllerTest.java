package org.karina.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.karina.factory.PrimeNumberCalculatorType;
import org.karina.model.Response;
import org.karina.service.impl.CalculatorServiceImpl;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(Controller.class)
public class ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CalculatorServiceImpl calculatorService;

    @InjectMocks
    Controller controller;

    static Integer n;
    static List<Integer> primes;
    static String algorithm;
    static Response response;

    @BeforeAll
    static void setup() {
        n = 10;
        primes = List.of(2,3,5,7);
        algorithm = "ERATOSTHENES";
        response = Response.builder().n(n).primeNumberList(primes.toString()).algorithm(algorithm).build();
    }

    @Test
    void getValidResponseWhenGivenNumber() throws Exception {
        when(calculatorService.getResponse(anyInt(), any(PrimeNumberCalculatorType.class))).thenReturn(response);

        mockMvc.perform(get("/api/calculate?n={n}", n))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.n").value(n))
                .andExpect(jsonPath("$.primeNumberList").value(primes.toString()))
                .andExpect(jsonPath("$.algorithm").value(algorithm))
                .andDo(print());

    }

    @Test
    void getValidResponseWhenGivenNumberAndAlgorithm() throws Exception {
        when(calculatorService.getResponse(anyInt(), any(PrimeNumberCalculatorType.class))).thenReturn(response);

        mockMvc.perform(get("/api/calculate?n={n}&algorithm={algorithm}", n, algorithm))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.n").value(n))
                .andExpect(jsonPath("$.primeNumberList").value(primes.toString()))
                .andExpect(jsonPath("$.algorithm").value(algorithm))
                .andDo(print());

    }

    @Test
    void getErrorResponseWhenGivenNumberLessThan2() throws Exception {
        when(calculatorService.getResponse(anyInt(), any(PrimeNumberCalculatorType.class))).thenThrow(new IllegalArgumentException("n must be greater than 1"));

        mockMvc.perform(get("/api/calculate?n={n}", 1))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("n must be greater than 1"))
                .andDo(print());

    }

    @Test
    void getErrorResponseWhenGivenNonValidAlgorithm() throws Exception {
        List<String> validAlgorithms = new ArrayList<>();
        for (PrimeNumberCalculatorType type : PrimeNumberCalculatorType.values()) {
            validAlgorithms.add(type.toString());
        }

        mockMvc.perform(get("/api/calculate?n={n}&algorithm={algorithm}", n, "INVALID"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid algorithm. Please choose one of the following: " + validAlgorithms))
                .andDo(print());

    }
}
