package org.karina.service.impl;

import org.karina.factory.CalculatorFactory;
import org.karina.factory.PrimeNumberCalculatorType;
import org.karina.model.Response;
import org.karina.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.karina.factory.PrimeNumberCalculatorType.ERATOSTHENES;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Autowired
    private CalculatorFactory calculatorFactory;

    @Override
    public Response getResponse(Integer n, PrimeNumberCalculatorType algorithm) {
        List<Integer> primeList = calculatorFactory.getCalculator(algorithm).calculate(n);
        return Response.builder()
                .n(n)
                .primeNumberList(primeList.toString())
                .algorithm(algorithm.toString())
                .build();
    }
}
