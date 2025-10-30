package org.karina.factory.impl;

import org.karina.calculator.PrimeNumberCalculator;
import org.karina.calculator.impl.SieveOfEratosthenes;
import org.karina.factory.CalculatorFactory;
import org.karina.factory.PrimeNumberCalculatorType;
import org.springframework.stereotype.Component;

import static org.karina.factory.PrimeNumberCalculatorType.ERATOSTHENES;

@Component
public class CalculatorFactoryImpl implements CalculatorFactory {
    @Override
    public PrimeNumberCalculator getCalculator(PrimeNumberCalculatorType algorithm) {
        PrimeNumberCalculator calculator;
        switch (algorithm) {
            case ERATOSTHENES:
                calculator = new SieveOfEratosthenes();
                break;
            default:
                calculator = new SieveOfEratosthenes();
        }
        return calculator;
    }
}
