package org.karina.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.karina.calculator.PrimeNumberCalculator;
import org.karina.calculator.impl.SieveOfEratosthenes;
import org.karina.calculator.impl.SieveOfSundaram;
import org.karina.factory.impl.CalculatorFactoryImpl;


public class CalculatorFactoryTest {

    static CalculatorFactory calculatorFactory;

    @BeforeAll
    static void setUp() {
        calculatorFactory = new CalculatorFactoryImpl();
    }

    @Test
    void getEratosthenesCalculator() {
        PrimeNumberCalculator calculator = calculatorFactory.getCalculator(PrimeNumberCalculatorType.ERATOSTHENES);
        Assertions.assertInstanceOf(SieveOfEratosthenes.class, calculator);
    }

    @Test
    void getSundaramCalculator() {
        PrimeNumberCalculator calculator = calculatorFactory.getCalculator(PrimeNumberCalculatorType.SUNDARAM);
        Assertions.assertInstanceOf(SieveOfSundaram.class, calculator);
    }
}
