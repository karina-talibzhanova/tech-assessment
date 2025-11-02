package org.karina.calculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedClass;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.karina.calculator.impl.SieveOfEratosthenes;
import org.karina.factory.CalculatorFactory;
import org.karina.factory.PrimeNumberCalculatorType;
import org.karina.factory.impl.CalculatorFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ParameterizedClass
@EnumSource(PrimeNumberCalculatorType.class)
public class PrimeNumberCalculatorTest {
    static CalculatorFactory calculatorFactory;

    @Parameter
    PrimeNumberCalculatorType algorithm;

    PrimeNumberCalculator calculator;

    @BeforeAll
    static void init() {
        calculatorFactory = new CalculatorFactoryImpl();
    }

    @BeforeEach
    void setUp() {
        calculator = calculatorFactory.getCalculator(algorithm);
    }

    @ParameterizedTest
    @MethodSource("generator")
    void testCalculator(int n, List<Integer> expected) {

        List<Integer> result = calculator.calculate(n);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> generator() {
        // get test data

        return Stream.of(
                Arguments.of(2, getPrimes(2)),
                Arguments.of(10, getPrimes(10)),
                Arguments.of(79, getPrimes(79)),
                Arguments.of(20354, getPrimes(20354)),
                Arguments.of(900500, getPrimes(900500)),
                Arguments.of(1000000, getPrimes(1000000))
        );
    }

    private static List<Integer> getPrimes(int n) {
        // prime number list was taken from https://www.mathsisfun.com/numbers/prime-number-lists.html
        List<String> primeFiles = List.of("1-primes-to-100k.txt", "2-primes-to-200k.txt", "3-primes-to-300k.txt", "4-primes-to-400k.txt", "5-primes-to-500k.txt", "6-primes-to-600k.txt", "7-primes-to-700k.txt", "8-primes-to-800k.txt", "9-primes-to-900k.txt", "10-primes-to-1000k.txt");
        List<Integer> primes = new ArrayList<>();

        for (String file : primeFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/primes/" + file))) {
                String line;
                Integer prime;
                while ((line = br.readLine()) != null) {
                    // process the line.
                    prime = Integer.parseInt(line);
                    if (prime <= n) {
                        primes.add(prime);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return primes;
    }
}
