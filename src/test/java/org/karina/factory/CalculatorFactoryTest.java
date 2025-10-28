package org.karina.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.karina.calculator.PrimeNumberCalculator;
import org.karina.calculator.impl.SieveOfEratosthenes;
import org.karina.factory.impl.CalculatorFactoryImpl;


public class CalculatorFactoryTest {

    static CalculatorFactory calculatorFactory;

    @BeforeAll
    static void setUp() {
        calculatorFactory = new CalculatorFactoryImpl();
    }

    @Test
    void testGetEratosthenesCalculator() {
        PrimeNumberCalculator calculator = calculatorFactory.getCalculator("ERATOSTHENES");
        Assertions.assertInstanceOf(SieveOfEratosthenes.class, calculator);
    }
}
