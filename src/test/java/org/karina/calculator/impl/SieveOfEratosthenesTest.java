package org.karina.calculator.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SieveOfEratosthenesTest {
    private static SieveOfEratosthenes calculator;

    @BeforeAll
    static void setup() {
        calculator = new SieveOfEratosthenes();
    }

    @Test
    void testCalculator() {
        List<Integer> expected = List.of(2,3,5,7);

        List<Integer> result = calculator.calculate(10);

        assertEquals(expected, result);
    }
}
