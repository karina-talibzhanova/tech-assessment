package org.karina.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.karina.factory.CalculatorFactory;
import org.karina.service.impl.CalculatorServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {
    @Mock
    CalculatorFactory calculatorFactory;

    @InjectMocks
    CalculatorServiceImpl calculatorService;

    @Test
    void getValidResponseWhenGivenNumber() throws Exception {

    }

    @Test
    void getValidResponseWhenGivenNumberAndAlgorithm() throws Exception {

    }

    @Test
    void getThrowsExceptionWhenNumberIsLessThan2() throws Exception {

    }
}
