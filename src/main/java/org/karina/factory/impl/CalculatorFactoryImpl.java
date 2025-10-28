package org.karina.factory.impl;

import org.karina.calculator.PrimeNumberCalculator;
import org.karina.calculator.impl.SieveOfEratosthenes;
import org.karina.factory.CalculatorFactory;
import org.springframework.stereotype.Component;

@Component
public class CalculatorFactoryImpl implements CalculatorFactory {
    @Override
    public PrimeNumberCalculator getCalculator(String algorithm) {
        return new SieveOfEratosthenes();
    }
}
