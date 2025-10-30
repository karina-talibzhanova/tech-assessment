package org.karina.service;

import org.karina.factory.PrimeNumberCalculatorType;
import org.karina.model.Response;

public interface CalculatorService {
    Response getResponse(Integer n);
    Response getResponse(Integer n, PrimeNumberCalculatorType algorithm);
}
