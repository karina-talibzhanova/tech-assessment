package org.karina.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.karina.calculator.impl.SieveOfEratosthenes;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeNumberCalculatorTest {

    @ParameterizedTest
    @MethodSource("generator")
    void testCalculator(PrimeNumberCalculator calculator, int n, List<Integer> expected) {

        List<Integer> result = calculator.calculate(n);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> generator() {
        // get test data

        return Stream.of(
                Arguments.of(new SieveOfEratosthenes(), 10, List.of(2,3,5,7))
        );
    }
}
