package org.karina.factory.impl;

import org.karina.calculator.PrimeNumberCalculator;
import org.karina.calculator.impl.SieveOfAtkin;
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
            case SUNDARAM:
                calculator = new SieveOfSundaram();
                break;
            case ATKIN:
                calculator = new SieveOfAtkin();
                break;
            case ERATOSTHENES:
            default:
                calculator = new SieveOfEratosthenes();
        }
        return calculator;
    }
}
