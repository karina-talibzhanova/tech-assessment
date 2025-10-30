package org.karina.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.karina.calculator.impl.SieveOfEratosthenes;
import org.karina.factory.CalculatorFactory;
import org.karina.factory.PrimeNumberCalculatorType;
import org.karina.model.Response;
import org.karina.service.impl.CalculatorServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {
    @Mock
    CalculatorFactory calculatorFactory;

    @InjectMocks
    CalculatorServiceImpl calculatorService;

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
    void getValidResponseWhenGivenNumberAndAlgorithm() {
        when(calculatorFactory.getCalculator(any(PrimeNumberCalculatorType.class))).thenReturn(new SieveOfEratosthenes());

        Response result = calculatorService.getResponse(n, PrimeNumberCalculatorType.ERATOSTHENES);

        assertEquals(n, result.getN());
        assertEquals(primes.toString(), response.getPrimeNumberList());
        assertEquals(algorithm, response.getAlgorithm());

    }

    @Test
    void getThrowsExceptionWhenNumberIsLessThan2() {

        assertThrows(IllegalArgumentException.class, () -> calculatorService.getResponse(1, PrimeNumberCalculatorType.ERATOSTHENES));

    }
}
