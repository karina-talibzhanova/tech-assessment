package org.karina.factory;

import org.karina.calculator.PrimeNumberCalculator;

public interface CalculatorFactory {
    PrimeNumberCalculator getCalculator(PrimeNumberCalculatorType algorithm);
}
