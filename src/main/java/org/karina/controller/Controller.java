package org.karina.controller;

import org.karina.model.Response;
import org.karina.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping(path = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getPrimes(@RequestParam Integer n, @RequestParam(required = false) String algorithm) {
        Response response = calculatorService.getResponse(n);
        return ResponseEntity.ok(response);
    }
}
