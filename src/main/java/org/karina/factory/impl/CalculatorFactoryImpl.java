package org.karina.factory.impl;

import org.karina.calculator.PrimeNumberCalculator;
import org.karina.calculator.impl.SieveOfEratosthenes;
import org.karina.calculator.impl.SieveOfSundaram;
import org.karina.factory.CalculatorFactory;
import org.karina.factory.PrimeNumberCalculatorType;
import org.springframework.stereotype.Component;


@Component
public class CalculatorFactoryImpl implements CalculatorFactory {
    @Override
    public PrimeNumberCalculator getCalculator(PrimeNumberCalculatorType algorithm) {
        PrimeNumberCalculator calculator;
        switch (algorithm) {
            case ERATOSTHENES:
                calculator = new SieveOfEratosthenes();
                break;
            case SUNDARAM:
                calculator = new SieveOfSundaram();
                break;

            default:
                calculator = new SieveOfEratosthenes();
        }
        return calculator;
    }
}
