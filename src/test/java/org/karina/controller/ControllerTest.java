package org.karina.controller;

import org.junit.jupiter.api.Test;
import org.karina.service.impl.CalculatorServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(Controller.class)
public class ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    CalculatorServiceImpl calculatorService;

    @InjectMocks
    Controller controller;

    @Test
    void getValidResponseWhenGivenNumber() throws Exception {

    }

    @Test
    void getValidResponseWhenGivenNumberAndAlgorithm() throws Exception {

    }

    @Test
    void getErrorResponseWhenGivenNumberLessThan2() throws Exception {

    }

    @Test
    void getErrorResponseWhenGivenNonValidAlgorithm() throws Exception {

    }
}
